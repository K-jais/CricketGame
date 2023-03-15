package com.kushjaiswal.cricketgame.displayModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatsmanFigureDisplay {
    private int position;
    private String playerName;
    private int runs;
    private int balls;
    private double strikeRate;
    private boolean isNotOut;
}
