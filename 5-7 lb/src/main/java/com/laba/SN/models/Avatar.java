/**
 * Класс Avatar представляет сущность для хранения данных об аватаре пользователя.
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "avatars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avatar {

    /**
     * Идентификатор аватара.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    /**
     * Имя файла аватара.
     */
    @Column(name="name")
    private String name;

    /**
     * Оригинальное имя файла аватара.
     */
    @Column(name="originalFileName")
    private String originalFileName;

    /**
     * Размер файла аватара.
     */
    @Column(name="size")
    private Long size;

    /**
     * Тип содержимого файла аватара.
     */
    @Column(name="contentType")
    private String contentType;

    /**
     * Байтовое представление файла аватара.
     */
    @Lob
    private byte[] bytes;

    /**
     * Пользователь, которому принадлежит аватар.
     */
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    /**
     * Генерирует хэш-код для аватара на основе его свойств.
     *
     * @return Хэш-код аватара.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, originalFileName, size, contentType);
    }

    /**
     * Возвращает строковое представление объекта Avatar.
     *
     * @return Строковое представление объекта Avatar.
     */
    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originalFileName='" + originalFileName + '\'' +
                ", size=" + size +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
