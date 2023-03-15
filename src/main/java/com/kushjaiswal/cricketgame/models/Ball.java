package com.kushjaiswal.cricketgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ball {
    @Id
    private int ballId;
    private String batsmanOnStrike;
    private String result;
    private int overId;
}
