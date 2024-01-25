/**
 * Класс PostController обрабатывает запросы, связанные с созданием, просмотром и удалением постов.
 *
 * @author [Ваше Имя]
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.controllers;

import com.laba.SN.models.Post;
import com.laba.SN.services.PostService;
import com.laba.SN.services.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final VisitService visitService;

    /**
     * Обрабатывает GET-запрос для страницы создания нового поста.
     *
     * @param principal Текущий аутентифицированный пользователь.
     * @param model     Модель для добавления атрибутов для отображения в представлении.
     * @return Название представления для создания нового поста.
     */
    @GetMapping("/post-new")
    public String newPost(Principal principal, Model model) {
        return "post-new";
    }

    /**
     * Обрабатывает POST-запрос для создания нового поста.
     *
     * @param file1      Первый файл для загрузки.
     * @param file2      Второй файл для загрузки.
     * @param file3      Третий файл для загрузки.
     * @param otherFile  Дополнительный файл для загрузки.
     * @param post       Объект поста с данными.
     * @param principal  Текущий аутентифицированный пользователь.
     * @param model      Модель для добавления атрибутов для отображения в представлении.
     * @return Перенаправление на главную страницу после создания поста.
     * @throws IOException Исключение, возникающее при работе с файлами.
     */
    @PostMapping("/post/create")
    public String createPost(@RequestParam("file1") MultipartFile file1,
                             @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3,
                             @RequestParam("otherFile") MultipartFile otherFile,
                             Post post, Principal principal, Model model) throws IOException {
        postService.savePost(principal, post, file1, file2, file3, otherFile);
        return "redirect:/";
    }

    /**
     * Обрабатывает GET-запрос для страницы с информацией о конкретном посте.
     *
     * @param id        Идентификатор поста.
     * @param principal Текущий аутентифицированный пользователь.
     * @param model     Модель для добавления атрибутов для отображения в представлении.
     * @return Название представления для информации о посте.
     */
    @GetMapping("/post/{id}")
    public String postInfo(@PathVariable Long id, Principal principal, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("user", postService.getUserByPrincipal(principal));
        model.addAttribute("post", post);
        model.addAttribute("counter", visitService.getCounter());
        model.addAttribute("images", post.getImages());
        model.addAttribute("file", post.getFile());
        return "post-info";
    }

    /**
     * Обрабатывает POST-запрос для удаления поста.
     *
     * @param id Идентификатор поста, который нужно удалить.
     * @return Перенаправление на главную страницу после удаления поста.
     */
    @PostMapping("/post/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/";
    }
}
