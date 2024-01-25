/**
 * Перечисление Role представляет роли пользователей в системе.
 * Реализует интерфейс GrantedAuthority для использования в Spring Security.
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    /**
     * Роль обычного пользователя.
     */
    ROLE_USER,

    /**
     * Роль администратора.
     */
    ROLE_ADMIN,

    /**
     * Роль модератора.
     */
    ROLE_MODER;

    /**
     * Получает наименование роли в виде строки.
     *
     * @return Наименование роли.
     */
    @Override
    public String getAuthority() {
        return name();
    }
}
