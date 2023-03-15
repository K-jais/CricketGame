package com.kushjaiswal.cricketgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inning {
    @Id
    private int inningId;
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
