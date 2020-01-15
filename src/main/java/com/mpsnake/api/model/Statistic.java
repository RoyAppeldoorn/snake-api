package com.mpsnake.api.model;

import lombok.*;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Statistic {

    @Id
    private String player_id;

    private Integer kills;

    private Integer deads;

    public Statistic(String playerId) {
        this.player_id = playerId;
    }
}