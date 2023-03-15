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
public class OneTeamScorecard {
    private String teamName;
    private int teamTotal;
    private int totalWicketsGone;
    private double totalOversBowled;
    private List<BatsmanFigureDisplay> batsmanFiguresDiplayList;
    private int extras;
    private double runRate;
    private List<BowlerFigureDisplay> bowlerFiguresDiplayList;
    private List<FallOfWicketDisplay> fallOfWicketDisplayList;
    private boolean isTossWin;

}
