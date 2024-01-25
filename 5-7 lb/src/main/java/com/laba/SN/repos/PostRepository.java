/**
 * Интерфейс репозитория для работы с сущностью Post.
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.repos;

import com.laba.SN.models.Post;
import com.laba.SN.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Интерфейс репозитория для работы с сущностью Post.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Находит список постов по заголовку.
     *
     * @param title Заголовок поста.
     * @return Список постов с указанным заголовком.
     */
    List<Post> findByTitle(String title);

    /**
     * Находит уникальные посты, созданные указанным пользователем.
     *
     * @param user Пользователь, создавший посты.
     * @return Список уникальных постов, созданных указанным пользователем.
     */
    List<Post> findDistinctByUser(User user);

    // Можно добавить дополнительные методы, если необходимо
}
