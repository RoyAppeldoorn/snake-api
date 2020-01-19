package com.mpsnake.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Statistic {

    @Id
    @JsonProperty("player_id")
    @NotEmpty
    private String player_id;

    @Column(nullable = true)
    private Integer kills;

    @Column(nullable = true)
    private Integer deads;

    public Statistic(String playerId) {
        this.player_id = playerId;
        this.kills = 0;
        this.deads = 0;
    }
}