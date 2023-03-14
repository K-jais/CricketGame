package com.kushjaiswal.cricketgame.displayModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerRecordAgainst {
    private int position;
    private String name;
    private String teamName;
    private String teamAgainst;
    private int runs;
    private int wickets;
    private double strikeRate;
    private double average;
    private double economy;
}
