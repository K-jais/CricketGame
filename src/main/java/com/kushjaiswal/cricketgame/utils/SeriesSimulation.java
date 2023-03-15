package com.kushjaiswal.cricketgame.utils;

import com.kushjaiswal.cricketgame.dto.GameAutomationDto;
import com.kushjaiswal.cricketgame.dto.SeriesAutomationDto;
import com.kushjaiswal.cricketgame.dto.TossAutomationDto;
import com.kushjaiswal.cricketgame.repository.series.SeriesRepository;
import com.kushjaiswal.cricketgame.service.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeriesSimulation {
    @Autowired
    SeriesRepository seriesRepository;
    @Autowired
    TossSimulation tossSimulation;
    @Autowired
    GameService gameService;

    public void simulate(SeriesAutomationDto seriesSimulationDto) {
        int team1_id = seriesSimulationDto.getTeam1Id();
        int team2_id = seriesSimulationDto.getTeam2Id();
        int totalOvers = seriesSimulationDto.getTotalOvers();
        int totalGames = seriesSimulationDto.getTotalGames();
        String sponsor = seriesSimulationDto.getSponsor();
        int series_id = seriesRepository.addSeries(seriesSimulationDto);
        TossAutomationDto tossSimulationDto = new TossAutomationDto();
        tossSimulationDto.setTeam1Id(team1_id);
        tossSimulationDto.setTeam2Id(team2_id);
        tossSimulationDto.setTotalOvers(totalOvers);
        for (int i = 0; i < totalGames; i++) {
            GameAutomationDto gameSimulationDto = tossSimulation.tossSimulate(tossSimulationDto);
            gameSimulationDto.setSeries(series_id);
            gameService.gameSimulation(gameSimulationDto);
        }
    }

}
