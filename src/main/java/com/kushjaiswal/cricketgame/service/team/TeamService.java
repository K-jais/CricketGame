package com.kushjaiswal.cricketgame.service.team;

import com.kushjaiswal.cricketgame.dto.TeamDto;
import com.kushjaiswal.cricketgame.models.Team;

public interface TeamService {
    Team getById(int id);

    TeamDto addTeam(TeamDto teamDto);

    TeamDto updateTeam(TeamDto teamDto, int id);

    String deleteTeam(int id);

    Team getTeamByName(String name);

    int getTeamWonByName(String name);

    int getTeamLoseByName(String name);
}
