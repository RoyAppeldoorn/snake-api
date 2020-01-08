package com.mpsnake.api.controller;

import com.mpsnake.api.logic.PlayerLogic;
import com.mpsnake.api.model.Player;
import com.mpsnake.api.repositories.PlayerRepository;
import com.mpsnake.api.repositories.StatisticsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {
    private final PlayerRepository repository;

    @Autowired
    PlayerLogic playerLogic;

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    PlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/player/create")
    public void createPlayer(@RequestBody Player newPlayer) {
        playerLogic.createPlayer(newPlayer);
    }

    @PutMapping("/player/{id}")
    public Player updatePlayer(@RequestBody Player newPlayer, @PathVariable String id) {
        return repository.findById(id)
                .map(player -> {
                    player.setNickname(newPlayer.getNickname());
                    return repository.save(player);
                })
                .orElseGet(() -> {
                    newPlayer.setPlayer_id(id);
                    return repository.save(newPlayer);
                });
    }

    @DeleteMapping("/player/{id}")
    public void deletePlayer(@PathVariable String id) {
        repository.deleteById(id);
    }
}
