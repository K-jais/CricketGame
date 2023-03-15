package com.kushjaiswal.cricketgame.service.game.impl;

import com.kushjaiswal.cricketgame.dto.GamePlayedDto;
import com.kushjaiswal.cricketgame.dto.GameAutomationDto;
import com.kushjaiswal.cricketgame.models.Game;
import com.kushjaiswal.cricketgame.models.Team;
import com.kushjaiswal.cricketgame.repository.game.GameRepository;
import com.kushjaiswal.cricketgame.service.game.GameService;
import com.kushjaiswal.cricketgame.utils.GameSimulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    GameSimulation gameSimulation;


    @Override
    public Game getById(int id) {
        return gameRepository.getById(id);
    }

    @Override
    public Team getWinningTeam(int id) {
        return gameRepository.getWinningTeam(id);
    }

    @Override
    public String getManOfMatch(int id) {
        return gameRepository.getManOfMatch(id);
    }

    @Override
    public void gameSimulation(GameAutomationDto gameSimulationDto) {
        gameSimulation.simulate(gameSimulationDto);
    }

    @Override
    public List<GamePlayedDto> getMatchesPlayedByATeam(String name) {
        return gameRepository.getMatchesPlayedByATeam(name);
    }
}
