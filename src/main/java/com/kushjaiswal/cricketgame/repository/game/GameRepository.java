package com.kushjaiswal.cricketgame.repository.game;

import com.kushjaiswal.cricketgame.dto.GameDto;
import com.kushjaiswal.cricketgame.dto.GamePlayedDto;
import com.kushjaiswal.cricketgame.models.Game;
import com.kushjaiswal.cricketgame.models.Team;

import java.util.List;

public interface GameRepository {
    Game getById(int id);

    Team getWinningTeam(int id);

    String getManOfMatch(int id);

    List<GamePlayedDto> getMatchesPlayedByATeam(String name);

    int addGame(GameDto gameDto);

    List<Game> getSeriesMatches(int id);

    List<Game> getMatchesBetween(int teamId, int teamId1);
}
