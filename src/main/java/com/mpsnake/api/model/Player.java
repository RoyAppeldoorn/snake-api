package com.mpsnake.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Player {
    @Id
    @JsonProperty("player_id")
    private String player_id;

    @JsonProperty("nickname")
    private String nickname;
}