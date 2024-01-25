/**
 * Класс FileController обрабатывает запросы для загрузки файлов.
 *
 * @author [Ваше Имя]
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.controllers;

import com.laba.SN.models.File;
import com.laba.SN.repos.FileRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RestController
public class FileController {
    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * Обрабатывает GET-запрос для загрузки файла по его идентификатору.
     *
     * @param id Идентификатор файла.
     * @return ResponseEntity с ресурсом файла и заголовками.
     */
    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        Optional<File> optionalFile = fileRepository.findById(id);

        if (optionalFile.isPresent()) {
            File file = optionalFile.get();
            byte[] fileContent = file.getBytes();

            ByteArrayResource resource = new ByteArrayResource(fileContent);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(file.getContentType()));
            headers.setContentLength(file.getSize());
            String encodedFileName = UriUtils.encode(file.getOriginalFileName(), StandardCharsets.UTF_8);

            headers.setContentDispositionFormData("attachment", encodedFileName);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } else {
            // Если файл не найден, можно вернуть ResponseEntity с соответствующим статусом
            return ResponseEntity.notFound().build();
        }
    }
}
