package com.kushjaiswal.cricketgame.repository.team.impl;

import com.kushjaiswal.cricketgame.dto.TeamDto;
import com.kushjaiswal.cricketgame.models.Team;
import com.kushjaiswal.cricketgame.repository.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRepositoryImpl implements TeamRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_TEAM_BY_ID = "select * from Team where team_id=?";
    private static final String ADD_TEAM = "insert into Team(name,matchesWin,matchesLose) values(?,?,?)";
    private static final String UPDATE_TEAM = "update Team set name=?,matchesWin=?,matchesLose=? where team_id=?";
    private static final String DELETE_TEAM = "delete from Team where team_id=?";
    private static final String GET_TEAM_BY_NAME = "select * from Team where name=?";
    private static final String GET_MATCHES_WIN = "select matchesWin from Team where name=?";
    private static final String GET_MATCHES_LOSE = "select matchesLose from Team where name=?";

    private static final String INCREASE_WINS = "update Team set matchesWin=matchesWin+1 where team_id=?";

    private static final String INCREASE_LOSS = "update Team set matchesLose=matchesLose+1 where team_id=?";


    @Override
    public Team getById(int id) {
        return jdbcTemplate.queryForObject(GET_TEAM_BY_ID, (rs, rowNum) -> {
            return new Team(rs.getInt("team_id"), rs.getString("name"), rs.getInt("matchesWin"), rs.getInt("matchesLose"));
        }, id);
    }

    @Override
    public TeamDto addTeam(TeamDto teamDto) {
        jdbcTemplate.update(ADD_TEAM, teamDto.getName(), teamDto.getMatchesWin(), teamDto.getMatchesLose());
        return teamDto;
    }

    @Override
    public TeamDto updateTeam(TeamDto teamDto, int id) {
        jdbcTemplate.update(UPDATE_TEAM, teamDto.getName(), teamDto.getMatchesWin(), teamDto.getMatchesLose(), id);
        return teamDto;
    }

    @Override
    public void deleteTeam(int id) {
        jdbcTemplate.update(DELETE_TEAM, id);
    }

    @Override
    public Team getTeamByName(String name) {
        return jdbcTemplate.queryForObject(GET_TEAM_BY_NAME, (rs, rowNum) -> {
            return new Team(rs.getInt("team_id"), rs.getString("name"), rs.getInt("matchesWin"), rs.getInt("matchesLose"));
        }, name);
    }

    @Override
    public int getTeamWonByName(String name) {
        return jdbcTemplate.queryForObject(GET_MATCHES_WIN, (rs, rowNum) -> {
            return rs.getInt("matchesWin");
        }, name);
    }

    @Override
    public int getTeamLoseByName(String name) {
        return jdbcTemplate.queryForObject(GET_MATCHES_LOSE, (rs, rowNum) -> {
            return rs.getInt("matchesLose");
        }, name);
    }

    @Override
    public void increaseWins(int id) {
        jdbcTemplate.update(INCREASE_WINS, id);
    }

    @Override
    public void increaseLoss(int id) {
        jdbcTemplate.update(INCREASE_LOSS, id);
    }
}
