package com.kushjaiswal.cricketgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BowlerFigures {
    @Id
    private int bowlsFigId;
    private int wickets;
    private int runsGiven;
    private int inningId;
    private int position;
}
