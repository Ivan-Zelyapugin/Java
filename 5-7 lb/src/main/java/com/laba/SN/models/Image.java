/**
 * Класс Image представляет сущность для хранения данных об изображении, прикрепленном к посту.
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    /**
     * Идентификатор изображения.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    /**
     * Имя изображения.
     */
    @Column(name="name")
    private String name;

    /**
     * Оригинальное имя файла изображения.
     */
    @Column(name="originalFileName")
    private String originalFileName;

    /**
     * Размер изображения.
     */
    @Column(name="size")
    private Long size;

    /**
     * Тип содержимого изображения.
     */
    @Column(name="contentType")
    private String contentType;

    /**
     * Флаг, указывающий, является ли изображение предварительным просмотром.
     */
    @Column(name="isPreviewImage")
    private boolean isPreviewImage;

    /**
     * Байтовое представление изображения.
     */
    @Lob
    private byte[] bytes;

    /**
     * Пост, к которому прикреплено изображение.
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Post post;
}
