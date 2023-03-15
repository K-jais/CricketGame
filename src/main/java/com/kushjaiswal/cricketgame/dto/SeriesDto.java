package com.kushjaiswal.cricketgame.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeriesDto {
    private int team1Id;
    private int team2Id;
    private int totalOvers;
    private int totalGames;
    private String sponsor;
    private int team1Wins;
    private int team2Wins;
}
