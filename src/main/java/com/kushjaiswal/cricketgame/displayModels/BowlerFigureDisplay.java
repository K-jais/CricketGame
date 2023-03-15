package com.kushjaiswal.cricketgame.displayModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BowlerFigureDisplay {
    private int position;
    private String playerName;
    private int totalOvers;
    private int runsGiven;
    private int wickets;
    private double economy;
}
