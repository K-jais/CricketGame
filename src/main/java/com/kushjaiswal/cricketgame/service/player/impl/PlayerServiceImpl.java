package com.kushjaiswal.cricketgame.service.player.impl;

import com.kushjaiswal.cricketgame.dto.PlayerDto;
import com.kushjaiswal.cricketgame.models.Player;
import com.kushjaiswal.cricketgame.repository.player.PlayerRepository;
import com.kushjaiswal.cricketgame.service.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Player getById(int id) {
        return playerRepository.getById(id);
    }

    @Override
    public PlayerDto addPlayer(PlayerDto playerDto) {
        return playerRepository.addPlayer(playerDto);
    }

    @Override
    public PlayerDto updatePlayer(PlayerDto playerDto, int id) {
        return playerRepository.updatePlayer(playerDto, id);
    }

    @Override
    public String deletePlayer(int id) {
        playerRepository.deletePlayer(id);
        return "Player is Deleted";
    }

    @Override
    public List<Player> getPlayersOfTeam(int id) {
        return playerRepository.getPlayersOfTeam(id);
    }
}
