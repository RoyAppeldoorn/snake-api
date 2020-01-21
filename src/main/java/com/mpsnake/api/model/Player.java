package com.mpsnake.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Player {
    @Id
    @JsonProperty("player_id")
    @NotEmpty
    private String player_id;

    @JsonProperty("nickname")
    @NotEmpty
    private String nickname;
}