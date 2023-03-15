package com.kushjaiswal.cricketgame.dto;

import com.kushjaiswal.cricketgame.models.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private String name;
    private int age;
    private int runsScored;
    private int wicketsTaken;
    private int numberOfCenturies;
    private int fiveWicketHalls;
    private Player.BattingBehaviour battingBehaviour;
    private Player.BowlingBehaviour bowlingBehaviour;
    private int position;
    private int teamId;

    public enum BattingBehaviour {
        Opening, MiddleOrder, Tailender
    }

    public enum BowlingBehaviour {
        Main, PartTime, Non
    }

}
