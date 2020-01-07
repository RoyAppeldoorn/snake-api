package com.mpsnake.api.controller;

import com.mpsnake.api.model.Player;
import com.mpsnake.api.repositories.PlayerRepository;
import com.mpsnake.api.repositories.StatisticsRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {
    private final PlayerRepository repository;

    PlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/player")
    Player newPlayer(@RequestBody Player newPlayer) {
        return repository.save(newPlayer);
    }

    @PutMapping("/player/{id}")
    Player updatePlayer(@RequestBody Player newPlayer, @PathVariable String id) {
        return repository.findById(id)
                .map(player -> {
                    player.setDisplay_name(newPlayer.getDisplay_name());
                    return repository.save(player);
                })
                .orElseGet(() -> {
                    newPlayer.setId(id);
                    return repository.save(newPlayer);
                });
    }

    @DeleteMapping("/player/{id}")
    void deletePlayer(@PathVariable String id) {
        repository.deleteById(id);
    }
}
