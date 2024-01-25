/**
 * Класс AvatarController обрабатывает запросы, связанные с получением аватаров пользователей.
 *
 * @author [Сафронов, зеляпугин]
 *  * @version 1.0
 *  * @since [21.12.23]
 */

package com.laba.SN.controllers;

import com.laba.SN.models.Avatar;
import com.laba.SN.repos.AvatarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class AvatarController {
    private final AvatarRepository avatarRepository;

    /**
     * Обрабатывает GET-запрос для получения аватара по его идентификатору.
     *
     * @param id Идентификатор аватара.
     * @return ResponseEntity с информацией и содержимым аватара.
     */
    @GetMapping("/avatar/{id}")
    private ResponseEntity<?> getAvatarId(@PathVariable Long id) {
        Avatar avatar = avatarRepository.findById(id).orElse(null);

        if (avatar != null) {
            return ResponseEntity.ok()
                    .header("fileName", avatar.getOriginalFileName())
                    .contentType(MediaType.valueOf(avatar.getContentType()))
                    .contentLength(avatar.getSize())
                    .body(new InputStreamResource(new ByteArrayInputStream(avatar.getBytes())));
        } else {
            // Если аватар не найден, можно вернуть ResponseEntity с соответствующим статусом
            return ResponseEntity.notFound().build();
        }
    }
}
