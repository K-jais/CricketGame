package com.kushjaiswal.cricketgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scorecard {
    @Id
    private int scorecardId;
    private int inning1Id;
    private int inning2Id;
    private int gameId;
}
