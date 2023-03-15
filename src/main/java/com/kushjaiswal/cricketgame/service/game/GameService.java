package com.kushjaiswal.cricketgame.service.game;

import com.kushjaiswal.cricketgame.dto.GamePlayedDto;
import com.kushjaiswal.cricketgame.dto.GameAutomationDto;
import com.kushjaiswal.cricketgame.models.Game;
import com.kushjaiswal.cricketgame.models.Team;

import java.util.List;

public interface GameService {
    Game getById(int id);

    Team getWinningTeam(int id);

    String getManOfMatch(int id);

    void gameSimulation(GameAutomationDto gameSimulationDto);

    List<GamePlayedDto> getMatchesPlayedByATeam(String name);
}
