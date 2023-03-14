package com.kushjaiswal.cricketgame.displayModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmallScorecard {
    private String matchResult;
    private OneTeamScorecard firstInningScorecard;
    private OneTeamScorecard secondInningScorecard;
    private String manOfMatchName;
    private String manOfMatchFigures;
}
