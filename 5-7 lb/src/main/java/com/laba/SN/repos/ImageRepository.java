/**
 * Интерфейс репозитория для работы с сущностью Image.
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.repos;

import com.laba.SN.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс репозитория для работы с сущностью Image.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
    // Можно добавить дополнительные методы, если необходимо
}
