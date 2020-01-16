package com.mpsnake.api.logic;

import com.mpsnake.api.model.Player;
import com.mpsnake.api.model.Statistic;
import com.mpsnake.api.repositories.StatisticsRepository;
import com.mpsnake.api.utilities.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class StatisticsLogic {
    private StatisticsRepository statisticsRepository;

    private final Logger logger = LoggerFactory.getLogger(StatisticsLogic.class);

    public StatisticsLogic(StatisticsRepository statisticsRepo) {
        this.statisticsRepository = statisticsRepo;
    }

    public void createNewStatistic(String id) {
        try {
            Statistic newPlayer = new Statistic(id);
            statisticsRepository.save(newPlayer);
        } catch (Exception ex) {
            LoggerUtil.errorLogging(ex.toString());
        }
    }

    public Statistic getUserStatistics(String id) throws ResponseStatusException {
        Optional<Statistic> statistic = null;
        try {
            statistic = statisticsRepository.findById(id);
        } catch (Exception ex) {
            LoggerUtil.errorLogging(ex.toString());
        }

        statistic.orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found")
        );

        return statistic.get();
    }

    public void increaseKillCountForPlayer(String id) {
        try {
            Statistic stat = getUserStatistics(id);
            if(stat.getKills() == null) {
                stat.setKills(1);
            } else {
                stat.setKills(stat.getKills() + 1);
            }
            statisticsRepository.save(stat);
        } catch(Exception ex) {
            LoggerUtil.errorLogging(ex.toString());
        }
    }

    public void increaseDeadCountForPlayer(String id) {
        try {
            Statistic stat = getUserStatistics(id);
            if(stat.getDeads() == null) {
                stat.setDeads(1);
            } else {
                stat.setDeads(stat.getDeads() + 1);
            }
            statisticsRepository.save(stat);
        } catch(Exception ex) {
            LoggerUtil.errorLogging(ex.toString());
        }
    }
}
