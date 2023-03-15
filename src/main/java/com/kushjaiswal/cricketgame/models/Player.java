package com.kushjaiswal.cricketgame.models;

import com.kushjaiswal.cricketgame.utils.bowlingbehaviour.BowlingBehaviour;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    private int playerId;
    private String name;
    private int age;
    private int runsScored;
    private int wicketsTaken;
    private int numberOfCenturies;
    private int fiveWicketHalls;
    private BattingBehaviour battingBehaviour;
    private BowlingBehaviour bowlingBehaviour;
    private int position;
    private int teamId;

    public enum BattingBehaviour {
        Opening, MiddleOrder, Tailender
    }

    public enum BowlingBehaviour {
        Main, PartTime, Non
    }
}
