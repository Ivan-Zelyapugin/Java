/**
 * Класс ModerController обрабатывает запросы, связанные с модераторскими функциями.
 *
 * @author [Ваше Имя]
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.controllers;

import com.laba.SN.repos.PostRepository;
import com.laba.SN.services.PostService;
import com.laba.SN.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_MODER')")
public class ModerController {
    private final UserService userService;
    private final PostService postService;

    /**
     * Обрабатывает GET-запрос для страницы модератора.
     *
     * @param model     Модель для добавления атрибутов для отображения в представлении.
     * @param principal Текущий аутентифицированный пользователь.
     * @return Название представления для страницы модератора.
     */
    @GetMapping("/moder")
    public String moder(Model model, Principal principal) {
        model.addAttribute("users", userService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "moder";
    }

    /**
     * Обрабатывает POST-запрос для удаления поста модератором.
     *
     * @param id Идентификатор поста, который нужно удалить.
     * @return Перенаправление на страницу модератора после удаления поста.
     */
    @PostMapping("/moder/post/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/moder";
    }
}
