package com.kushjaiswal.cricketgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatsmanFiguresDto {
    private int runs;
    private int balls;
    private int inningId;
    private boolean isNotout;
    private int position;

    public void increaseRuns(int runs) {
        this.runs += runs;
    }

    public void increaseBalls() {
        ++this.balls;
    }

    public void decreaseBalls() {
        --this.balls;
    }

}
