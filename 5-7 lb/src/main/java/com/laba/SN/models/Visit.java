/**
 * Класс Visit представляет сущность для хранения информации о посещениях сайта.
 *
 * @version 1.0
 * @since [Дата]
 */

package com.laba.SN.models;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Сущность для хранения информации о посещениях сайта.
 */
@Entity
@EntityScan
@Data
public class Visit {

    /**
     * Идентификатор посещения.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Количество посещений сайта.
     */
    private int count;

    /**
     * Получение идентификатора посещения.
     *
     * @return Идентификатор посещения.
     */
    public Long getId() {
        return id;
    }

    /**
     * Установка идентификатора посещения.
     *
     * @param id Новый идентификатор посещения.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получение количества посещений сайта.
     *
     * @return Количество посещений сайта.
     */
    public int getCount() {
        return count;
    }

    /**
     * Установка количества посещений сайта.
     *
     * @param count Новое количество посещений сайта.
     */
    public void setCount(int count) {
        this.count = count;
    }

    // геттеры, сеттеры и другие методы (если необходимо)
}
