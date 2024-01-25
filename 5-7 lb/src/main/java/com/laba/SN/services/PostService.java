/**
 * Сервис для работы с постами.
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.services;

import com.laba.SN.models.File;
import com.laba.SN.models.Image;
import com.laba.SN.models.Post;
import com.laba.SN.models.User;
import com.laba.SN.repos.PostRepository;
import com.laba.SN.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

/**
 * Сервис для работы с постами.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    /**
     * Получает список всех постов или фильтрует их по заголовку.
     *
     * @param title Заголовок для фильтрации, или null для получения всех постов.
     * @return Список постов, удовлетворяющих условиям фильтрации.
     */
    public List<Post> listPosts(String title){
        List<Post> posts = (List<Post>) postRepository.findAll();

        if(title!=null) return postRepository.findByTitle(title);
        return postRepository.findAll();
    }

    /**
     * Получает список постов пользователя.
     *
     * @param principal Объект Principal, представляющий аутентифицированного пользователя.
     * @return Список постов пользователя.
     */
    public List<Post> getUserPosts(Principal principal) {
        User user = getUserByPrincipal(principal);
        return postRepository.findDistinctByUser(user);
    }

    /**
     * Сохраняет новый пост.
     *
     * @param principal Аутентифицированный пользователь.
     * @param post Объект поста для сохранения.
     * @param file1, file2, file3, otherFile Мультимедийные файлы для поста.
     * @throws IOException Возникает при ошибке чтения файлов.
     */
    public void savePost(Principal principal, Post post, MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile otherFile) throws IOException {
        post.setUser(getUserByPrincipal(principal));
        post.setActive(true);
        Image image1;
        Image image2;
        Image image3;
        File file;
        if(file1.getSize()!=0){
            image1=toImageEntity(file1);
            image1.setPreviewImage(true);
            post.addImageToPost(image1);
        }
        if(file2.getSize()!=0){
            image2=toImageEntity(file2);
            post.addImageToPost(image2);
        }
        if(file3.getSize()!=0){
            image3=toImageEntity(file3);
            post.addImageToPost(image3);
        }
        if (otherFile.getSize() != 0) {
            file=toFileEntity(otherFile);
            post.addFileToPost(file);
        }
        log.info("Saving new Post. title:{}; email:{}", post.getTitle(), post.getUser().getEmail());
        Post postFromDb=postRepository.save(post);
        if(!file1.isEmpty()||!file2.isEmpty()||!file3.isEmpty()) postFromDb.setPreviewImageId(postFromDb.getImages().get(0).getId());
        postRepository.save(post);
    }

    /**
     * Получает пользователя по Principal.
     *
     * @param principal Объект Principal, представляющий аутентифицированного пользователя.
     * @return Пользователь, представленный Principal.
     */
    public User getUserByPrincipal(Principal principal) {
        if(principal==null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    /**
     * Удаляет или восстанавливает пост.
     *
     * @param id Идентификатор поста для удаления или восстановления.
     */
    public void deletePost(Long id){
        Post post = postRepository.getById(id);
        if (post != null) {
            if (post.isActive()) {
                post.setActive(false);
                log.info("Delete post with id = {}; title: {}", post.getId(), post.getTitle());
            } else {
                post.setActive(true);
                log.info("Undelete post with id = {}; title: {}", post.getId(), post.getTitle());
            }
        }
        postRepository.save(post);
    }

    /**
     * Получает пост по его идентификатору.
     *
     * @param id Идентификатор поста.
     * @return Объект поста, если найден; в противном случае, возвращает null.
     */
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    private File toFileEntity(MultipartFile file) throws IOException{
        File file1 = new File();
        file1.setName(file.getName());
        file1.setOriginalFileName(file.getOriginalFilename());
        file1.setContentType(file.getContentType());
        file1.setSize(file.getSize());
        file1.setBytes(file.getBytes());
        return file1;
    }

    /**
     * Преобразует мультимедийный файл в объект изображения.
     *
     * @param file Мультимедийный файл для преобразования.
     * @return Объект изображения.
     * @throws IOException Возникает при ошибке чтения файла.
     */
    private Image toImageEntity(MultipartFile file) throws IOException{
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}
