/**
 * Класс MainController обрабатывает запросы для основных функций приложения.
 *
 * @author [Ваше Имя]
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.controllers;

import com.laba.SN.models.Post;
import com.laba.SN.models.User;
import com.laba.SN.services.PostService;
import com.laba.SN.services.VisitService;
import com.laba.SN.repos.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final PostRepository postRepository;
    private final VisitService visitService;
    private final PostService postService;

    /**
     * Обрабатывает GET-запрос для главной страницы.
     *
     * @param principal Текущий аутентифицированный пользователь.
     * @param model     Модель для добавления атрибутов для отображения в представлении.
     * @return Название представления для главной страницы.
     */
    @GetMapping("/")
    public String home(Principal principal, Model model) {
        model.addAttribute("user", postService.getUserByPrincipal(principal));
        model.addAttribute("posts", postRepository.findAll());

        visitService.incrementCounter();
        model.addAttribute("counter", visitService.getCounter());
        return "home";
    }

    /**
     * Обрабатывает GET-запрос для страницы пользователя.
     *
     * @param principal Текущий аутентифицированный пользователь.
     * @param model     Модель для добавления атрибутов для отображения в представлении.
     * @return Название представления для страницы пользователя.
     */
    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        User user = postService.getUserByPrincipal(principal);

        model.addAttribute("user", user);
        model.addAttribute("posts", user.getPosts());
        model.addAttribute("counter", visitService.getCounter());

        return "user";
    }

    /**
     * Обрабатывает GET-запрос для завершения сеанса пользователя (выхода из системы).
     *
     * @param model     Модель для добавления атрибутов для отображения в представлении.
     * @param principal Текущий аутентифицированный пользователь.
     * @return Перенаправление на главную страницу после выхода из системы.
     */
    @GetMapping("/logout")
    public String logout(Model model, Principal principal) {
        // Ваш код для завершения сеанса пользователя, если необходимо
        // Например, вы можете использовать Spring Security метод для выхода
        SecurityContextHolder.getContext().setAuthentication(null);

        // После завершения сеанса, перенаправьте пользователя на нужную страницу
        return "redirect:/"; // Можно выбрать любой URL
    }
}
