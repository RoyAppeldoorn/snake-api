package com.mpsnake.api.model;

import lombok.*;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Player {
    @Id
    private String player_id;

    private String display_name;
}