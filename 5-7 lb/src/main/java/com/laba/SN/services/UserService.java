/**
 * Сервис для базовой логики регистрации
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.services;

import com.laba.SN.models.Avatar;
import com.laba.SN.models.Post;
import com.laba.SN.models.User;
import com.laba.SN.models.enums.Role;
import com.laba.SN.repos.AvatarRepository;
import com.laba.SN.repos.PostRepository;
import com.laba.SN.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Сервис для базовой логики регистрации.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final AvatarRepository avatarRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Создает нового пользователя на основе данных из формы регистрации.
     *
     * @param user Объект пользователя для создания.
     * @return true, если пользователь успешно создан; в противном случае - false.
     */
    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false; // Ручная валидация
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(userRepository.count()==0)
            user.getRoles().add(Role.ROLE_ADMIN);
        user.getRoles().add(Role.ROLE_USER);
        log.info("Сохранен новый пользовател с email: {}", email);
        userRepository.save(user);
        return true;
    }

    /**
     * Загружает аватар пользователя.
     *
     * @param principal Аутентифицированный пользователь.
     * @param avatar Файл аватара.
     * @throws IOException Возникает при ошибке чтения файла.
     */
    public void uploadAvatar(Principal principal, MultipartFile avatar) throws IOException {
        Avatar image;
        User user = getUserByPrincipal(principal);

        if (avatar.getSize() != 0) {
            if (user.getAvatarId() != null) {
                Avatar old = user.getAvatar();
                avatarRepository.delete(old);
            }
            image = toAvatarEntity(avatar, user);
            avatarRepository.save(image);
            user.setAvatarId(image.getId());
            user.addAvatarToUser(image);

            log.info("Saving new avatar. ");
            userRepository.save(user);
        }
    }

    /**
     * Редактирует данные пользователя.
     *
     * @param user Объект пользователя для редактирования.
     */
    public void editUser(User user){
        String email =user.getEmail();
        if (userRepository.findByEmail(email) == null) return; // Ручная валидация
        userRepository.save(user);
    }

    private Avatar toAvatarEntity(MultipartFile file, User user) throws IOException {
        Avatar image = new Avatar();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        image.setUser(user);
        return image;
    }

    /**
     * Удаляет аватар пользователя.
     *
     * @param userEmail Email пользователя.
     */
    public void deleteAvatar(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user != null) {
            Avatar avatar = user.getAvatar();
            if (avatar != null) {
                user.setAvatarId(null);
                userRepository.save(user);
                avatarRepository.delete(avatar);
            }
        }
    }

    /**
     * Получает пользователя по Principal.
     *
     * @param principal Объект Principal, представляющий аутентифицированного пользователя.
     * @return Пользователь, представленный Principal.
     */
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    /**
     * Получает список всех пользователей.
     *
     * @return Список всех пользователей.
     */
    public List<User> list() {
        return userRepository.findAll();
    }

    /**
     * Получает пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Объект пользователя, если найден; в противном случае, возвращает null.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Заблокировать или разблокировать пользователя.
     *
     * @param id Идентификатор пользователя.
     */
    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
        }
        userRepository.save(user);
    }

    /**
     * Изменить роли пользователя.
     *
     * @param user Объект пользователя для изменения ролей.
     * @param form Карта ролей, полученных из формы.
     */
    public void changeUserRoles(User user, Map<String, String> form) {
        Set<Role> availableRoles = Arrays.stream(Role.values()).collect(Collectors.toSet());

        log.info("User {} roles before change: {}", user.getEmail(), user.getRoles());

        Set<Role> newRoles = form.entrySet().stream()
                .filter(entry -> entry.getValue().equalsIgnoreCase("on")) // Сравниваем с "on"
                .filter(entry -> availableRoles.contains(Role.valueOf(entry.getKey())))
                .map(entry -> Role.valueOf(entry.getKey()))
                .collect(Collectors.toSet());

        log.info("New roles from form: {}", newRoles);

        user.setRoles(newRoles);

        log.info("User {} roles after change: {}", user.getEmail(), user.getRoles());

        userRepository.save(user);
        log.info("User roles updated and saved: {}", user.getRoles());
    }
}
