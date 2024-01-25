/**
 * Класс UserController обрабатывает запросы, связанные с пользователями.
 *
 * @author [Ваше Имя]
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.controllers;

import com.laba.SN.models.Image;
import com.laba.SN.models.Post;
import com.laba.SN.models.User;
import com.laba.SN.services.PostService;
import com.laba.SN.services.UserService;
import com.laba.SN.services.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Обрабатывает GET-запрос для страницы входа в систему.
     *
     * @return Название представления для страницы входа.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Обрабатывает POST-запрос для входа в систему.
     *
     * @param principal Текущий аутентифицированный пользователь.
     * @param model     Модель для добавления атрибутов для отображения в представлении.
     * @return Перенаправление на главную страницу после входа в систему.
     */
    @PostMapping("/login")
    public String login(Principal principal, Model model) {
        // Другие действия, которые вам нужны при входе в систему
        return "redirect:/"; // Или куда вам нужно перенаправить после входа
    }

    /**
     * Обрабатывает POST-запрос для создания нового пользователя.
     *
     * @param user  Объект пользователя с данными для регистрации.
     * @param model Модель для добавления атрибутов для отображения в представлении.
     * @return Название представления для страницы входа после регистрации.
     */
    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с адресом " + user.getEmail() + " уже существует");
            //return "login";
        }
        return "login";
    }

    /**
     * Обрабатывает GET-запрос для страницы информации о конкретном пользователе.
     *
     * @param user      Объект пользователя, информацию о котором нужно отобразить.
     * @param model     Модель для добавления атрибутов для отображения в представлении.
     * @param principal Текущий аутентифицированный пользователь.
     * @return Название представления для страницы информации о пользователе.
     */
    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("usr", user);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("posts", user.getPosts());
        return "user-info";
    }

    /**
     * Обрабатывает GET-запрос для страницы редактирования данных пользователя.
     *
     * @param principal Текущий аутентифицированный пользователь.
     * @param model     Модель для добавления атрибутов для отображения в представлении.
     * @return Название представления для страницы редактирования данных пользователя.
     */
    @GetMapping("/user/edit")
    public String userEdit(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "user-edit";
    }

    /**
     * Обрабатывает POST-запрос для сохранения измененных данных пользователя.
     *
     * @param principal Текущий аутентифицированный пользователь.
     * @param model     Модель для добавления атрибутов для отображения в представлении.
     * @param user      Объект пользователя с измененными данными.
     * @return Перенаправление на страницу пользователя после сохранения изменений.
     */
    @PostMapping("/user/edit")
    public String userChange(Principal principal, Model model, User user) {
        User oldUser = userService.getUserByPrincipal(principal);
        oldUser.setFirst_name(user.getFirst_name());
        oldUser.setSecond_name(user.getSecond_name());
        oldUser.setBirthday(user.getBirthday());
        userService.editUser(oldUser);
        return "redirect:/user";
    }

    /**
     * Обрабатывает POST-запрос для загрузки аватара пользователя.
     *
     * @param avatar    Файл с изображением аватара.
     * @param principal Текущий аутентифицированный пользователь.
     * @return Перенаправление на страницу пользователя после загрузки аватара.
     * @throws IOException Исключение, возникающее при работе с файлами.
     */
    @PostMapping("/user/uploadAvatar")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile avatar, Principal principal) throws IOException {
        userService.uploadAvatar(principal, avatar);
        return "redirect:/user";
    }

    /**
     * Обрабатывает POST-запрос для удаления аватара пользователя.
     *
     * @param principal Текущий аутентифицированный пользователь.
     * @return Перенаправление на страницу пользователя после удаления аватара.
     */
    @PostMapping("/user/deleteAvatar")
    public String deleteAvatar(Principal principal) {
        userService.deleteAvatar(principal.getName());
        return "redirect:/user";
    }
}
