package com.mpsnake.api.model;

import lombok.*;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Statistics {
    @Id
    @GeneratedValue
    private String player_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private Player player;

    private Integer kills;

    private Integer deads;
}