package com.mpsnake.api.repositories;

import com.mpsnake.api.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatisticsRepository extends JpaRepository<Statistic, String> {
    @Query(value = "SELECT s FROM Statistic s WHERE s.player_id = :playerid")
    Statistic getUserRecord(@Param("playerid") String id);
}

