package com.kushjaiswal.cricketgame.utils.bowlingbehaviour.types;

import com.kushjaiswal.cricketgame.utils.battingbehaviour.BattingBehaviour;
import com.kushjaiswal.cricketgame.utils.battingbehaviour.types.MiddleOrderBatsman;
import com.kushjaiswal.cricketgame.utils.battingbehaviour.types.OpeningBatsman;
import com.kushjaiswal.cricketgame.utils.battingbehaviour.types.TailenderBatsman;
import com.kushjaiswal.cricketgame.utils.bowlingbehaviour.BowlingBehaviour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class PartTimeBowler implements BowlingBehaviour {
    @Autowired
    BattingBehaviour battingBehaviour;

    public BattingBehaviour getBattingBehaviour() {
        return this.battingBehaviour;
    }

    public void setBattingBehaviour(BattingBehaviour battingBehaviour) {
        this.battingBehaviour = battingBehaviour;
    }

    public void bowling() {
        new ArrayList();
        List probabilities;
        if (this.battingBehaviour instanceof OpeningBatsman) {
            probabilities = Arrays.asList(10, 15, 15, 3, 15, 12, 4, 5);
            ((OpeningBatsman)this.battingBehaviour).setProbabilities(probabilities);
        } else if (this.battingBehaviour instanceof MiddleOrderBatsman) {
            probabilities = Arrays.asList(10, 15, 15, 3, 20, 17, 7, 5);
            ((MiddleOrderBatsman)this.battingBehaviour).setProbabilities(probabilities);
        } else if (this.battingBehaviour instanceof TailenderBatsman) {
            probabilities = Arrays.asList(18, 15, 10, 3, 8, 3, 10, 5);
            ((TailenderBatsman)this.battingBehaviour).setProbabilities(probabilities);
        }

    }
}

