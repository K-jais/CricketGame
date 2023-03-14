package com.kushjaiswal.cricketgame.controllers;

import com.kushjaiswal.cricketgame.dto.PlayerDto;
import com.kushjaiswal.cricketgame.models.Player;
import com.kushjaiswal.cricketgame.service.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @GetMapping("/id/{playerId}")
    public Player getById(@PathVariable("playerId") int id) {
        return playerService.getById(id);
    }

    @PostMapping("/add")
    public PlayerDto addPlayer(@RequestBody PlayerDto playerDto) {
        return playerService.addPlayer(playerDto);
    }

    @PutMapping("/update/{playerId}")
    public PlayerDto updatePlayer(@RequestBody PlayerDto playerDto, @PathVariable("playerId") int id) {
        return playerService.updatePlayer(playerDto, id);
    }

    @DeleteMapping("/delete/{playerId}")
    public String deletePlayer(@PathVariable("playerId") int id) {
        return playerService.deletePlayer(id);
    }

    @GetMapping("/team/{teamId}")
    public List<Player> getPlayersOfTeam(@PathVariable("teamId") int id) {
        return playerService.getPlayersOfTeam(id);
    }
}
