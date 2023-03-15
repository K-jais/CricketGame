package com.kushjaiswal.cricketgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamePlayedDto {
    private int gameId;
    private String team1Name;
    private String team2Name;
    private String winningTeamName;
    private String manOfMatch;
    private String manOfMatchFigures;
}
