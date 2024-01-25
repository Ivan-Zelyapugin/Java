/**
 * Класс AdminController обрабатывает запросы, связанные с административными задачами и управлением пользователями.
 *
 * @author [Сафронов, зеляпугин]
 * @version 1.0
 * @since [21.12.23]
 */

package com.laba.SN.controllers;

import com.laba.SN.models.User;
import com.laba.SN.models.enums.Role;
import com.laba.SN.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    /**
     * Обрабатывает GET-запрос для страницы администратора.
     *
     * @param model      Модель для добавления атрибутов для отображения в представлении.
     * @param principal  Принципал (аккаунт) текущего аутентифицированного пользователя.
     * @return Название представления для страницы администратора.
     */
    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", userService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("usr", userService.getUserByPrincipal(principal));
        return "admin";
    }

    /**
     * Обрабатывает POST-запрос для блокировки пользователя.
     *
     * @param id Идентификатор пользователя, который будет заблокирован.
     * @return Перенаправляет на страницу администратора.
     */
    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    /**
     * Обрабатывает GET-запрос для редактирования данных пользователя.
     *
     * @param user       Пользователь, данные которого будут отредактированы.
     * @param model      Модель для добавления атрибутов для отображения в представлении.
     * @param principal  Принципал (аккаунт) текущего аутентифицированного пользователя.
     * @return Название представления для редактирования пользователя.
     */
    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "admin-user-edit";
    }

    /**
     * Обрабатывает POST-запрос для сохранения отредактированных данных пользователя.
     *
     * @param userId Идентификатор пользователя, данные которого будут отредактированы.
     * @param form   Данные формы с изменениями в ролях пользователя.
     * @return Перенаправляет на страницу администратора.
     */
    @PostMapping("/admin/user/edit/{id}")
    public String userEdit(@PathVariable("id") Long userId, @RequestParam Map<String, String> form) {
        log.info("Внутри метода userEdit. Идентификатор пользователя: {}, Форма: {}", userId, form);
        User user = userService.getUserById(userId);
        userService.changeUserRoles(user, form);
        return "redirect:/admin";
    }
}
