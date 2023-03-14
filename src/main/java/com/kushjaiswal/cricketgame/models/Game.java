package com.kushjaiswal.cricketgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    private int gameId;
    private int team1Id;
    private int team2Id;
    private int winningTeamId;
    private String manOfMatch;
    private String manOfMatchFigures;
    private int seriesId;
}
