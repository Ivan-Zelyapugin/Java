package com.laba.SN.controllers;

import com.laba.SN.services.TimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * класс для работы вывода времени
 */
@Slf4j
@RestController
@RequestMapping("/api/time")
public class TimeController {

    @Autowired
    private TimeService timeService;
    /**
     * Обрабатывает запрос на получения времени
     * @return актуальное время
     */
    @GetMapping
    public String getCurrentTime() {
        return timeService.getCurrentTime();
    }
}