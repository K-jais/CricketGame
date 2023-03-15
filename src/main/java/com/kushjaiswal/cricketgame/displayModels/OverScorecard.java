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
public class OverScorecard {
    private String firstInningBowlingTeamName;
    private List<OverInning> firstInning;
    private String secondInningBowlingTeamName;
    private List<OverInning> secondInning;
}
