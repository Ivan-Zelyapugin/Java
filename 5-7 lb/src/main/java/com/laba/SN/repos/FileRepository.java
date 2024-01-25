/**
 * Интерфейс репозитория для работы с сущностью File.
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.repos;

import com.laba.SN.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс репозитория для работы с сущностью File.
 */
public interface FileRepository extends JpaRepository<File, Long> {
    // Можно добавить дополнительные методы, если необходимо
}
