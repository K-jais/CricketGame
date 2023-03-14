package com.kushjaiswal.cricketgame.displayModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeriesScorecard {
    private String team1;
    private String team2;
    private String result;
    private String sponsor;
    private int totalGames;
    private int totalOvers;
    private List<SmallScorecard> smallScorecardList;
}
