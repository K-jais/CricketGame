package com.kushjaiswal.cricketgame.utils.bowlingbehaviour.types;

import com.kushjaiswal.cricketgame.utils.battingbehaviour.BattingBehaviour;
import com.kushjaiswal.cricketgame.utils.battingbehaviour.types.MiddleOrderBatsman;
import com.kushjaiswal.cricketgame.utils.battingbehaviour.types.OpeningBatsman;
import com.kushjaiswal.cricketgame.utils.battingbehaviour.types.TailenderBatsman;
import com.kushjaiswal.cricketgame.utils.bowlingbehaviour.BowlingBehaviour;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MainBowler implements BowlingBehaviour {
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
            probabilities = Arrays.asList(10, 20, 22, 10, 12, 8, 7, 3);
            ((OpeningBatsman)this.battingBehaviour).setProbabilities(probabilities);
        } else if (this.battingBehaviour instanceof MiddleOrderBatsman) {
            probabilities = Arrays.asList(25, 20, 18, 5, 15, 12, 10, 3);
            ((MiddleOrderBatsman)this.battingBehaviour).setProbabilities(probabilities);
        } else if (this.battingBehaviour instanceof TailenderBatsman) {
            probabilities = Arrays.asList(30, 20, 15, 3, 3, 2, 18, 3);
            ((TailenderBatsman)this.battingBehaviour).setProbabilities(probabilities);
        }

    }
}
