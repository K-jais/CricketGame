package com.kushjaiswal.cricketgame.utils.battingbehaviour.types;

import com.kushjaiswal.cricketgame.utils.ProbabilityManipulation;
import com.kushjaiswal.cricketgame.utils.battingbehaviour.BattingBehaviour;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Primary
public class OpeningBatsman implements BattingBehaviour {
    List<Integer> events = new ArrayList(Arrays.asList(0, 1, 2, 3, 4, 6, 7, 8));
    List<Integer> probabilities = new ArrayList(Arrays.asList(7, 10, 15, 3, 30, 20, 15, 2));
    @Autowired
    ProbabilityManipulation probabilityManipulation;

    public List<Integer> getProbabilities() {
        return this.probabilities;
    }

    public void setProbabilities(List<Integer> probabilities) {
        this.probabilities = probabilities;
    }

    public int batting() {
        return probabilityManipulation.myRand(this.events, this.probabilities, 8);
    }
}
