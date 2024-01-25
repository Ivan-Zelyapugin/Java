/**
 * Класс File представляет сущность для хранения данных о файле, прикрепленном к посту.
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
@Table(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

    /**
     * Идентификатор файла.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    /**
     * Имя файла.
     */
    @Column(name="name")
    private String name;

    /**
     * Оригинальное имя файла.
     */
    @Column(name="originalFileName")
    private String originalFileName;

    /**
     * Размер файла.
     */
    @Column(name="size")
    private Long size;

    /**
     * Тип содержимого файла.
     */
    @Column(name="contentType")
    private String contentType;

    /**
     * Байтовое представление файла.
     */
    @Lob
    private byte[] bytes;

    /**
     * Пост, к которому прикреплен файл.
     */
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Post post;
}
