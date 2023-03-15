package com.kushjaiswal.cricketgame.displayModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BallDisplay {
    private String batsmanOnStrike;
    private String result;
}
