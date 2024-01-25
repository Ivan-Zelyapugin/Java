/**
 * Интерфейс репозитория для работы с сущностью User.
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.repos;

import com.laba.SN.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория для работы с сущностью User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Находит пользователя по адресу электронной почты.
     *
     * @param email Адрес электронной почты пользователя.
     * @return Пользователь с указанным адресом электронной почты.
     */
    User findByEmail(String email);

    // Можно добавить дополнительные методы, если необходимо
}
