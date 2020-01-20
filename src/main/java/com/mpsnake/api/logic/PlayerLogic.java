package com.mpsnake.api.logic;

import com.mpsnake.api.model.Player;
import com.mpsnake.api.repositories.PlayerRepository;
import com.mpsnake.api.utilities.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlayerLogic {
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerLogic(PlayerRepository playerRepo) {
        this.playerRepository = playerRepo;
    }

    public void createPlayer(Player newPlayer) {
        try {
            playerRepository.save(newPlayer);
        } catch (Exception ex) {
            LoggerUtil.errorLogging(ex.toString());
        }
    }

    public Player getPlayer(String id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Player not found"));
    }

}
