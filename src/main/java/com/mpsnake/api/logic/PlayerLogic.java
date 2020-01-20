package com.mpsnake.api.logic;

import com.mpsnake.api.model.Player;
import com.mpsnake.api.model.Statistic;
import com.mpsnake.api.repositories.PlayerRepository;
import com.mpsnake.api.repositories.StatisticsRepository;
import com.mpsnake.api.utilities.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlayerLogic {
    private final PlayerRepository playerRepository;

    private final StatisticsRepository statisticsRepository;

    @Autowired
    public PlayerLogic(PlayerRepository playerRepo, StatisticsRepository statisticsRepo) {
        this.playerRepository = playerRepo;
        this.statisticsRepository = statisticsRepo;
    }

    public void createPlayer(Player newPlayer) {
        try {
            playerRepository.save(newPlayer);
            createNewStatisticOnCreatePlayer(newPlayer.getPlayer_id());
        } catch (Exception ex) {
            LoggerUtil.errorLogging(ex.toString());
        }
    }

    public Player getPlayer(String id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Player not found"));
    }

    private void createNewStatisticOnCreatePlayer(String id) {
        try {
            Statistic statistic = new Statistic(id);
            statisticsRepository.save(statistic);
        } catch (Exception ex) {
            LoggerUtil.errorLogging(ex.toString());
        }
    }

}
