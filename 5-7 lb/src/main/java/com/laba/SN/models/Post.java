/**
 * Класс Post представляет сущность для хранения данных о посте пользователя.
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    /**
     * Идентификатор поста.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Заголовок поста.
     */
    private String title, anons;

    /**
     * Текст поста.
     */
    @Column(name = "text", length = 1000)
    private String text;

    /**
     * Количество просмотров поста.
     */
    private int views;

    /**
     * Список изображений, прикрепленных к посту.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "post")
    private List<Image> images = new ArrayList<>();

    /**
     * Файл, прикрепленный к посту.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "post")
    private File file = new File();

    /**
     * Флаг, указывающий, активен ли пост.
     */
    private boolean active;

    /**
     * Идентификатор изображения для предварительного просмотра.
     */
    private Long previewImageId;

    /**
     * Пользователь, создавший пост.
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    /**
     * Дата создания поста.
     */
    private LocalDateTime dateOfCreated;

    /**
     * Инициализация даты создания перед сохранением в базу данных.
     */
    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    /**
     * Добавление изображения к посту.
     *
     * @param image Изображение для добавления.
     */
    public void addImageToPost(Image image) {
        image.setPost(this);
        images.add(image);
    }

    /**
     * Добавление файла к посту.
     *
     * @param file Файл для добавления.
     */
    public void addFileToPost(File file) {
        file.setPost(this);
        this.file = file;
    }

    /**
     * Генерация хэш-кода для поста на основе его свойств.
     *
     * @return Хэш-код поста.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, title, anons, text, views, previewImageId, dateOfCreated, active);
    }

    /**
     * Возвращает строковое представление объекта Post.
     *
     * @return Строковое представление объекта Post.
     */
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", anons='" + anons + '\'' +
                ", text='" + text + '\'' +
                ", views=" + views +
                ", previewImageId=" + previewImageId +
                ", dateOfCreated=" + dateOfCreated +
                ", active=" + active +
                '}';
    }
}
