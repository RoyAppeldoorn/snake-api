package com.mpsnake.api.controller;

import com.mpsnake.api.logic.PlayerLogic;
import com.mpsnake.api.logic.StatisticsLogic;
import com.mpsnake.api.model.Player;
import com.mpsnake.api.model.Statistic;
import com.mpsnake.api.repositories.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    StatisticsLogic statisticsLogic;

    StatisticsController(StatisticsRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/statistics/{id}/kill")
    public void increaseKillCountForPlayer(@PathVariable String id) {
        statisticsLogic.increaseKillCountForPlayer(id);
    }

    @PostMapping("/statistics/{id}/dead")
    public void increaseDeadCountForPlayer(@PathVariable String id) {
        statisticsLogic.increaseDeadCountForPlayer(id);
    }

    @GetMapping("/statistics/{id}")
    public Statistic getPlayerStatistics(@PathVariable String id) {
        return statisticsLogic.getUserStatistics(id);
    }
}
