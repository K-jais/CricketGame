package com.kushjaiswal.cricketgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Series {
    @Id
    private int seriesId;
    private int team1Id;
    private int team2Id;
    private int totalOvers;
    private int totalGames;
    private String sponsor;
    private int team1Wins;
    private int team2Wins;
}
