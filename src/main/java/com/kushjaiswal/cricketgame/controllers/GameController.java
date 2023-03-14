package com.kushjaiswal.cricketgame.controllers;

import com.kushjaiswal.cricketgame.dto.GamePlayedDto;
import com.kushjaiswal.cricketgame.dto.GameAutomationDto;
import com.kushjaiswal.cricketgame.dto.TossAutomationDto;
import com.kushjaiswal.cricketgame.models.Game;
import com.kushjaiswal.cricketgame.models.Team;
import com.kushjaiswal.cricketgame.service.game.GameService;
import com.kushjaiswal.cricketgame.utils.TossSimulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    GameService gameService;
    @Autowired
    TossSimulation tossSimulation;
    @PostMapping
    public String simulate(@RequestBody TossAutomationDto tossSimulationDto) {
        GameAutomationDto gameSimulatioDto = tossSimulation.tossSimulate(tossSimulationDto);
        gameSimulatioDto.setSeries(1);
        gameService.gameSimulation(gameSimulatioDto);
        return "Game Simulated Successfully";
    }
    @GetMapping("/id/{gameId}")
    public Game getById(@PathVariable("gameId") int id) {
        return gameService.getById(id);
    }

    @GetMapping("/winningteam/{gameId}")
    public Team getWinningTeam(@PathVariable("gameId") int id) {
        return gameService.getWinningTeam(id);
    }

    @GetMapping("/manofmatch/{gameId}")
    public String getManOfMatch(@PathVariable("gameId") int id) {
        return gameService.getManOfMatch(id);
    }

    @GetMapping("/teamgames/{teamName}")
    public List<GamePlayedDto> getMatchesPlayedByTeam(@PathVariable("teamName") String name) {
        return gameService.getMatchesPlayedByATeam(name);
    }
}
