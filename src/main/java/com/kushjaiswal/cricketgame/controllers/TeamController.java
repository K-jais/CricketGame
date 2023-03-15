package com.kushjaiswal.cricketgame.controllers;

import com.kushjaiswal.cricketgame.dto.TeamDto;
import com.kushjaiswal.cricketgame.models.Team;
import com.kushjaiswal.cricketgame.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;

    @GetMapping("/id/{teamId}")
    public Team getById(@PathVariable("teamId") int id) {
        return teamService.getById(id);
    }

    @PostMapping("/add")
    public TeamDto addTeam(@RequestBody TeamDto teamDto) {
        return teamService.addTeam(teamDto);
    }

    @PutMapping("/update/{teamId}")
    public TeamDto updateTeam(@RequestBody TeamDto teamDto, @PathVariable("teamId") int id) {
        return teamService.updateTeam(teamDto, id);
    }

    @DeleteMapping("/delete/{teamId}")
    public String deleteTeam(@PathVariable("teamId") int id) {
        return teamService.deleteTeam(id);
    }

    @GetMapping("/name/{teamName}")
    public Team getTeamByName(@PathVariable("teamName") String name) {
        return teamService.getTeamByName(name);
    }

    @GetMapping("/won/{teamName}")
    public int getTeamWonByName(@PathVariable("teamName") String name) {
        return teamService.getTeamWonByName(name);
    }

    @GetMapping("/lose/{teamName}")
    public int getTeamLoseByName(@PathVariable("teamName") String name) {
        return teamService.getTeamLoseByName(name);
    }
}
