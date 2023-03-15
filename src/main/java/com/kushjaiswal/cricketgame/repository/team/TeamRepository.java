package com.kushjaiswal.cricketgame.repository.team;

import com.kushjaiswal.cricketgame.dto.TeamDto;
import com.kushjaiswal.cricketgame.models.Team;

public interface TeamRepository {
    Team getById(int id);

    TeamDto addTeam(TeamDto teamDto);

    TeamDto updateTeam(TeamDto teamDto, int id);

    void deleteTeam(int id);

    Team getTeamByName(String name);

    int getTeamWonByName(String name);

    int getTeamLoseByName(String name);

    void increaseWins(int battingTeamId);

    void increaseLoss(int bowlingTeamId);
}
