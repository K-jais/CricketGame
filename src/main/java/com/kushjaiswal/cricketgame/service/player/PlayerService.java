package com.kushjaiswal.cricketgame.service.player;

import com.kushjaiswal.cricketgame.dto.PlayerDto;
import com.kushjaiswal.cricketgame.models.Player;

import java.util.List;

public interface PlayerService {
    Player getById(int id);

    PlayerDto addPlayer(PlayerDto playerDto);

    PlayerDto updatePlayer(PlayerDto playerDto, int id);

    String deletePlayer(int id);

    List<Player> getPlayersOfTeam(int id);
}
