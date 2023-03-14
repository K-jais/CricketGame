package com.kushjaiswal.cricketgame.repository.player;

import com.kushjaiswal.cricketgame.dto.BatsmanFiguresDto;
import com.kushjaiswal.cricketgame.dto.BowlerFiguresDto;
import com.kushjaiswal.cricketgame.dto.PlayerDto;
import com.kushjaiswal.cricketgame.models.Player;

import java.util.List;

public interface PlayerRepository {
    Player getById(int id);

    PlayerDto addPlayer(PlayerDto playerDto);

    PlayerDto updatePlayer(PlayerDto playerDto,int id);

    void deletePlayer(int id);

    List<Player> getPlayersOfTeam(int id);

    void increaseRunsScored(int battingTeamId, BatsmanFiguresDto batsmanFiguresDto);

    void increaseWicketsTaken(int bowlingTeamId, BowlerFiguresDto bowlerFiguresDto);
}
