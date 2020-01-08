package com.mpsnake.api.logic;

import com.mpsnake.api.model.Player;
import com.mpsnake.api.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerLogic {
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerLogic(PlayerRepository playerRepo) {
        this.playerRepository = playerRepo;
    }

    public void createPlayer(Player newPlayer) {
        playerRepository.save(newPlayer);
    }
}
