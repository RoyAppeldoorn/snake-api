package com.mpsnake.api.repositories;

import com.mpsnake.api.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {

}
