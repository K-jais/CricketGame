package com.kushjaiswal.cricketgame.service.team.impl;

import com.kushjaiswal.cricketgame.dto.TeamDto;
import com.kushjaiswal.cricketgame.models.Team;
import com.kushjaiswal.cricketgame.repository.team.TeamRepository;
import com.kushjaiswal.cricketgame.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository teamRepository;

    @Override
    public Team getById(int id) {
        return teamRepository.getById(id);
    }

    @Override
    public TeamDto addTeam(TeamDto teamDto) {
        return teamRepository.addTeam(teamDto);
    }

    @Override
    public TeamDto updateTeam(TeamDto teamDto, int id) {
        return teamRepository.updateTeam(teamDto, id);
    }

    @Override
    public String deleteTeam(int id) {
        teamRepository.deleteTeam(id);
        return "Team is Deleted";
    }

    @Override
    public Team getTeamByName(String name) {
        return teamRepository.getTeamByName(name);
    }

    @Override
    public int getTeamWonByName(String name) {
        return teamRepository.getTeamWonByName(name);
    }

    @Override
    public int getTeamLoseByName(String name) {
        return teamRepository.getTeamLoseByName(name);
    }
}
