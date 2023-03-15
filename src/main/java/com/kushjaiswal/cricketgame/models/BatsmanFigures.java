package com.kushjaiswal.cricketgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatsmanFigures {
    @Id
    private int batsFigId;
    private int runs;
    private int balls;
    private int inningId;
    private boolean isNotout;
    private int position;
}
