/**
 * Класс ImageController обрабатывает запросы, связанные с получением изображений.
 *
 * @author [Ваше Имя]
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.controllers;

import com.laba.SN.models.Image;
import com.laba.SN.repos.ImageRepository;
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
public class ImageController {
    private final ImageRepository imageRepository;

    /**
     * Обрабатывает GET-запрос для получения изображения по его идентификатору.
     *
     * @param id Идентификатор изображения.
     * @return ResponseEntity с информацией и содержимым изображения.
     */
    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        Image image = imageRepository.findById(id).orElse(null);

        if (image != null) {
            return ResponseEntity.ok()
                    .header("fileName", image.getOriginalFileName())
                    .contentType(MediaType.valueOf(image.getContentType()))
                    .contentLength(image.getSize())
                    .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
        } else {
            // Если изображение не найдено, можно вернуть ResponseEntity с соответствующим статусом
            return ResponseEntity.notFound().build();
        }
    }
}
