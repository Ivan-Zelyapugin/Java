/**
 * Интерфейс репозитория для работы с сущностью Avatar.
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.repos;

import com.laba.SN.models.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Интерфейс репозитория для работы с сущностью Avatar.
 */
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    // Можно добавить дополнительные методы, если необходимо
}
