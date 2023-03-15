package com.kushjaiswal.cricketgame.utils.bowlingbehaviour;

import com.kushjaiswal.cricketgame.utils.battingbehaviour.BattingBehaviour;

public interface BowlingBehaviour {
    void bowling();

    void setBattingBehaviour(BattingBehaviour var1);

    BattingBehaviour getBattingBehaviour();
}
