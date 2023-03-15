package com.kushjaiswal.cricketgame.displayModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HighestWicketTakerSeries {
    private int position;
    private String name;
    private int wickets;
    private double economy;
}
