package com.kushjaiswal.cricketgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossAutomationDto {
    private int team1Id;
    private int team2Id;
    private int totalOvers;
}
