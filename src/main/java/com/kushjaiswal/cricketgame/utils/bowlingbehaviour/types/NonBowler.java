package com.kushjaiswal.cricketgame.utils.bowlingbehaviour.types;

import com.kushjaiswal.cricketgame.utils.battingbehaviour.BattingBehaviour;
import com.kushjaiswal.cricketgame.utils.bowlingbehaviour.BowlingBehaviour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NonBowler implements BowlingBehaviour {
    @Autowired
    BattingBehaviour battingBehaviour;

    public void bowling() {
    }

    public BattingBehaviour getBattingBehaviour() {
        return this.battingBehaviour;
    }

    public void setBattingBehaviour(BattingBehaviour battingBehaviour) {
        this.battingBehaviour = battingBehaviour;
    }
}
