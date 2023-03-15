package com.kushjaiswal.cricketgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BowlerFiguresDto {
    private int wickets;
    private int runsGiven;
    private int inningId;
    private int position;

    public void increaseRunsGiven(int runsGiven) {
        this.runsGiven += runsGiven;
    }

    public void increaseWickets() {
        ++this.wickets;
    }
}
