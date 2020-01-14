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

    private Integer kills;

    private Integer deads;
}