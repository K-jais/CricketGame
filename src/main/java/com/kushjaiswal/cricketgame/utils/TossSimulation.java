package com.kushjaiswal.cricketgame.utils;

import com.kushjaiswal.cricketgame.dto.GameAutomationDto;
import com.kushjaiswal.cricketgame.dto.TossAutomationDto;
import org.springframework.stereotype.Service;

@Service
public class TossSimulation {
    public GameAutomationDto tossSimulate(TossAutomationDto tossSimulationDto) {
        int team1_id = tossSimulationDto.getTeam1Id();
        int team2_id = tossSimulationDto.getTeam2Id();
        GameAutomationDto gameSimulationDto = new GameAutomationDto();
        int coinToss = (int) (Math.random() * 2.0 + 0.0);
        if (coinToss == 1) {
            gameSimulationDto.setBattingTeamId(team2_id);
            gameSimulationDto.setBowlingTeamId(team1_id);
        } else {
            gameSimulationDto.setBattingTeamId(team1_id);
            gameSimulationDto.setBowlingTeamId(team2_id);
        }
        gameSimulationDto.setTotalOvers(tossSimulationDto.getTotalOvers());
        return gameSimulationDto;
    }
}
