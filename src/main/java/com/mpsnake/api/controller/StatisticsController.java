package com.mpsnake.api.controller;

import com.mpsnake.api.repositories.StatisticsRepository;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {
    private final StatisticsRepository repository;

    StatisticsController(StatisticsRepository repository) {
        this.repository = repository;
    }

}
