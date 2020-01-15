package com.mpsnake.api.logic;

import com.mpsnake.api.controller.StatisticsController;
import com.mpsnake.api.model.Statistic;
import com.mpsnake.api.repositories.StatisticsRepository;
import com.mpsnake.api.utilities.LoggerUtil;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StatisticsLogic {
    private StatisticsRepository statisticsRepository;

    private final Logger logger = LoggerFactory.getLogger(StatisticsLogic.class);

    public StatisticsLogic(StatisticsRepository statisticsRepo) {
        this.statisticsRepository = statisticsRepo;
    }

    public void increaseKillCountForPlayer(String id) {
        try {
            Statistic stat = this.statisticsRepository.findById(id).orElse(null);
            if(stat == null) {
                insertNewUser(id);
            } else {
                updateKillCountForPlayer(stat);
            }
        } catch (Exception ex){
            LoggerUtil.errorLogging(ex.toString());
        }
    }

    public void increaseDeadCountForPlayer(String id) {
        try {
            Statistic stat = this.statisticsRepository.findById(id).orElse(null);
            if(stat == null) {
                insertNewUser(id);
            } else {
                updateDeadCountForPlayer(stat);
            }
        } catch (Exception ex){
            LoggerUtil.errorLogging(ex.toString());
        }
    }

    public void updateKillCountForPlayer(Statistic statistic) {
        if (statistic.getKills() == null) {
            statistic.setKills(1);
        } else {
            statistic.setKills(statistic.getKills() + 1);
        }

        this.statisticsRepository.save(statistic);
    }

    public void updateDeadCountForPlayer(Statistic statistic) {
        if (statistic.getDeads() == null) {
            statistic.setDeads(1);
        } else {
            statistic.setDeads(statistic.getDeads() + 1);
        }

        this.statisticsRepository.save(statistic);
    }

    public void insertNewUser(String id) {
        Statistic newPlayer = new Statistic(id);
        this.statisticsRepository.save(newPlayer);
    }

    public Statistic getUserStatistics(String id) {
        Statistic statistic = null;
        try {
            statistic = this.statisticsRepository.findById(id).orElse(null);
        } catch (Exception ex) {
            LoggerUtil.errorLogging(ex.toString());
        }
        return statistic;
    }
}
