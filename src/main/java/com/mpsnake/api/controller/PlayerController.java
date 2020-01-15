package com.mpsnake.api.controller;

import com.mpsnake.api.logic.PlayerLogic;
import com.mpsnake.api.model.Player;
import com.mpsnake.api.repositories.PlayerRepository;
import com.mpsnake.api.repositories.StatisticsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/player")
@RestController
public class PlayerController {
    private final PlayerRepository repository;

    @Autowired
    PlayerLogic playerLogic;

    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    PlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    public void createPlayer(@RequestBody Player newPlayer) {
        playerLogic.createPlayer(newPlayer);
    }

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable String id) {
        return playerLogic.getPlayer(id);
    }

}
