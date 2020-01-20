package com.mpsnake.api.logic;

import com.mpsnake.api.model.Statistic;
import com.mpsnake.api.repositories.StatisticsRepository;
import com.mpsnake.api.utilities.LoggerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StatisticsLogic {
    private StatisticsRepository statisticsRepository;

    public StatisticsLogic(StatisticsRepository statisticsRepo) {
        this.statisticsRepository = statisticsRepo;
    }

    public void createNewStatistic(Statistic id) {
        try {
            statisticsRepository.save(id);
        } catch (Exception ex) {
            LoggerUtil.errorLogging(ex.toString());
        }
    }

    public Statistic getUserStatistics(String id) {
        return statisticsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));
    }

    public void increaseKillCountForPlayer(String id) {
        try {
            Statistic stat = getUserStatistics(id);
            stat.setKills(stat.getKills() + 1);
            statisticsRepository.save(stat);
        } catch(Exception ex) {
            LoggerUtil.errorLogging(ex.toString());
        }
    }

    public void increaseDeadCountForPlayer(String id) {
        try {
            Statistic stat = getUserStatistics(id);
            stat.setDeads(stat.getDeads() + 1);
            statisticsRepository.save(stat);
        } catch(Exception ex) {
            LoggerUtil.errorLogging(ex.toString());
        }
    }
}
