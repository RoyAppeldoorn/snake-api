package com.mpsnake.api.controller;

import com.mpsnake.api.logic.PlayerLogic;
import com.mpsnake.api.model.Player;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/player")
@RestController
@Api(value="player")
public class PlayerController {

    private final PlayerLogic playerLogic;

    @Autowired
    public PlayerController(PlayerLogic playerLogic) {
        this.playerLogic = playerLogic;
    }

    @PostMapping(value = "/create", produces = "application/json")
    public void createPlayer(@RequestBody Player newPlayer) {
        playerLogic.createPlayer(newPlayer);
    }

    @GetMapping(value="/{id}", produces = "application/json")
    public Player getPlayer(@PathVariable String id) {
        return playerLogic.getPlayer(id);
    }

}
