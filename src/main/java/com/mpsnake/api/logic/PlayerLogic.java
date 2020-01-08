package com.mpsnake.api.logic;

import com.mpsnake.api.controller.PlayerController;
import com.mpsnake.api.model.Player;
import com.mpsnake.api.repositories.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerLogic {
    private PlayerRepository playerRepository;

    private static final Logger logger = LoggerFactory.getLogger(PlayerLogic.class);

    @Autowired
    public PlayerLogic(PlayerRepository playerRepo) {
        this.playerRepository = playerRepo;
    }

    public void createPlayer(Player newPlayer) {
        playerRepository.save(newPlayer);
    }

    public Player getPlayer(String id) {
        return playerRepository.findById(id).orElse(null);
    }
}
