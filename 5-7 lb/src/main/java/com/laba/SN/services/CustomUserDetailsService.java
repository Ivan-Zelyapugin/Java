/**
 * Класс-сервис для пользовательской аутентификации.
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.services;

import com.laba.SN.models.User;
import com.laba.SN.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Класс-сервис для пользовательской аутентификации.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Загружает пользователя по его адресу электронной почты.
     *
     * @param email Адрес электронной почты пользователя.
     * @return Детали пользователя, если найден; в противном случае, выбрасывается исключение.
     * @throws UsernameNotFoundException Если пользователь с указанным адресом электронной почты не найден.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        return user;
    }
}
