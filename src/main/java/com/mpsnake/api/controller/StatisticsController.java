package com.mpsnake.api.controller;

import com.mpsnake.api.logic.StatisticsLogic;
import com.mpsnake.api.model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/statistics")
@RestController
public class StatisticsController {

    private final StatisticsLogic statisticsLogic;

    @Autowired
    public StatisticsController(StatisticsLogic statisticsLogic) {
        this.statisticsLogic = statisticsLogic;
    }

    @GetMapping("/{id}")
    public Statistic getPlayerStatistics(@PathVariable String id) {
        return statisticsLogic.getUserStatistics(id);
    }

    @PostMapping("/create")
    public void createNewStatistic(@RequestBody Statistic id) { statisticsLogic.createNewStatistic(id); }

    @PostMapping("/{id}/kill")
    public void increaseKillCountForPlayer(@PathVariable String id) {
        statisticsLogic.increaseKillCountForPlayer(id);
    }

    @PostMapping("/{id}/dead")
    public void increaseDeadCountForPlayer(@PathVariable String id) {
        statisticsLogic.increaseDeadCountForPlayer(id);
    }
}
