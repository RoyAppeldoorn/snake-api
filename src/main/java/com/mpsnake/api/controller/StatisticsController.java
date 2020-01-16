package com.mpsnake.api.controller;

import com.mpsnake.api.logic.PlayerLogic;
import com.mpsnake.api.logic.StatisticsLogic;
import com.mpsnake.api.model.Player;
import com.mpsnake.api.model.Statistic;
import com.mpsnake.api.repositories.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/statistics")
@RestController
public class StatisticsController {
    private final StatisticsRepository repository;

    @Autowired
    StatisticsLogic statisticsLogic;

    StatisticsController(StatisticsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Statistic getPlayerStatistics(@PathVariable String id) {
        return statisticsLogic.getUserStatistics(id);
    }

    @PostMapping("/create/{id}")
    public void createNewStatistic(@PathVariable String id) { statisticsLogic.createNewStatistic(id); }

    @PostMapping("/{id}/kill")
    public void increaseKillCountForPlayer(@PathVariable String id) {
        statisticsLogic.increaseKillCountForPlayer(id);
    }

    @PostMapping("/{id}/dead")
    public void increaseDeadCountForPlayer(@PathVariable String id) {
        statisticsLogic.increaseDeadCountForPlayer(id);
    }
}
