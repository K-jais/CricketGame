package com.kushjaiswal.cricketgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InningDto {
    private int totalOvers;
    private boolean isWinToss;
    private String teamName;
    private int totalRunsScored;
    private double totalOversBowled;
    private int totalWicketsGone;
    private int extras;
    private String highestScorer;
    private int highestScorerRuns;
    private int highestScorerBalls;
    private String highestWicketTaker;
    private int highestWicketTakerWickets;
    private int highestWicketTakerRunsGiven;
}
