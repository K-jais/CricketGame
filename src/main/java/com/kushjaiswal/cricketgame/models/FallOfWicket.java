package com.kushjaiswal.cricketgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FallOfWicket {
    @Id
    private int fallOfWicketId;
    private int runs;
    private int wicketNumber;
    private int inningId;
}
