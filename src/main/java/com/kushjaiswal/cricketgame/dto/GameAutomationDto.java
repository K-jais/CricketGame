package com.kushjaiswal.cricketgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameAutomationDto {
    private int battingTeamId;
    private int bowlingTeamId;
    private int totalOvers;
    private int series;
}
