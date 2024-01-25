/**
 * Интерфейс репозитория для работы с сущностью Visit.
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.repos;

import com.laba.SN.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс репозитория для работы с сущностью Visit.
 */
public interface VisitRepository extends JpaRepository<Visit, Long> {
    // Можно добавить дополнительные методы, если необходимо
}
