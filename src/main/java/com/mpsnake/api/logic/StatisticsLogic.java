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

    public void increaseKillCountForPlayer(String id) {
        try {
            Statistic stat = statisticsRepository.findById(id).orElse(null);
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
            Statistic stat = statisticsRepository.findById(id).orElse(null);
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

        statisticsRepository.save(statistic);
    }

    public void updateDeadCountForPlayer(Statistic statistic) {
        if (statistic.getDeads() == null) {
            statistic.setDeads(1);
        } else {
            statistic.setDeads(statistic.getDeads() + 1);
        }

        statisticsRepository.save(statistic);
    }

    public void insertNewUser(String id) {
        try {
            Statistic newPlayer = new Statistic(id);
            statisticsRepository.save(newPlayer);
        } catch (Exception ex) {
            LoggerUtil.errorLogging(ex.toString());
        }

    }

    public Statistic getUserStatistics(String id) throws ResponseStatusException{
        Optional<Statistic> statistic = null;
        try {
            statistic = statisticsRepository.findById(id);
            statistic.orElseThrow(() ->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "User not found")
            );
        } catch (Exception ex) {
            LoggerUtil.errorLogging(ex.toString());
        }
        return statistic.get();
    }
}
