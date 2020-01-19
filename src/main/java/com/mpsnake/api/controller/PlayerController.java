package com.mpsnake.api.controller;

import com.mpsnake.api.logic.PlayerLogic;
import com.mpsnake.api.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/player")
@RestController
public class PlayerController {

    private final PlayerLogic playerLogic;

    @Autowired
    public PlayerController(PlayerLogic playerLogic) {
        this.playerLogic = playerLogic;
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
