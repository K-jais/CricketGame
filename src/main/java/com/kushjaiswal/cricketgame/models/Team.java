package com.kushjaiswal.cricketgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    private int teamId;
    private String name;
    private int matchesWin;
    private int matchesLose;
}
