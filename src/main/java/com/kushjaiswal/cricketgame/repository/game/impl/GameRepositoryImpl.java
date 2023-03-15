package com.kushjaiswal.cricketgame.repository.game.impl;

import com.kushjaiswal.cricketgame.dto.GameDto;
import com.kushjaiswal.cricketgame.dto.GamePlayedDto;
import com.kushjaiswal.cricketgame.models.Game;
import com.kushjaiswal.cricketgame.models.Team;
import com.kushjaiswal.cricketgame.repository.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepositoryImpl implements GameRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_GAME_BY_ID = "select * from Game where game_id=?";
    private static final String GET_WINNING_TEAM_ID = "select winningTeam_id from Game where game_id=?";
    private static final String GET_WINNING_TEAM = "select * from Team where team_id=?";
    private static final String GET_MANOFMATCH = "select manOfMatchName,manOfMatchFigures from Game where game_id=?";
    private static final String GET_MATCHES_PLAYED = "select * from Game where team1_id=? or team2_id=?";
    private static final String GET_MATCHES_PLAYED_BETWEEN = "select * from Game where (team1_id=? and team2_id=?) or (team1_id=? and team2_id=?)";
    private static final String ADD_GAME = "insert into Game(team1_id,team2_id,winningTeam_id,manOfMatchName,manOfMatchFigures,series_id) values(?,?,?,?,?,?)";
    private static final String GET_SERIES_GAMES = "select * from Game where series_id=?";
    private static final String GET_LAST_ID = "select last_insert_id()";

    @Override
    public Game getById(int id) {
        return jdbcTemplate.queryForObject(GET_GAME_BY_ID, (rs, rowNum) -> {
            return new Game(rs.getInt("game_id"), rs.getInt("team1_id"), rs.getInt("team2_id"), rs.getInt("winningTeam_id"), rs.getString("manOfMatchName"), rs.getString("manOfMatchFigures"), rs.getInt("series_id"));
        }, id);
    }

    @Override
    public Team getWinningTeam(int id) {
        int winningTeamId = getWinningTeamId(id);
        return jdbcTemplate.queryForObject(GET_WINNING_TEAM, (rs, rowNum) -> {
            return new Team(rs.getInt("team_id"), rs.getString("name"), rs.getInt("matchesWin"), rs.getInt("matchesLose"));
        }, winningTeamId);
    }

    @Override
    public String getManOfMatch(int id) {
        return jdbcTemplate.queryForObject(GET_MANOFMATCH, (rs, rowNum) -> {
            return rs.getString("manOfMatchName") + " " + rs.getString("manOfMatchFigures");
        }, id);
    }

    @Override
    public List<GamePlayedDto> getMatchesPlayedByATeam(String name) {
        int teamId = getTeamId(name);
        return jdbcTemplate.query(GET_MATCHES_PLAYED, (rs, rowNum) -> {
            String team1Name = getTeamName(rs.getInt("team1_id"));
            String team2Name = getTeamName(rs.getInt("team2_id"));
            String winningTeamName = getTeamName(rs.getInt("winningTeam_id"));
            return new GamePlayedDto(rs.getInt("game_id"), team1Name, team2Name, winningTeamName, rs.getString("manOfMatchName"), rs.getString("manOfMatchFigures"));
        }, teamId, teamId);
    }

    @Override
    public int addGame(GameDto gameDto) {
        jdbcTemplate.update(ADD_GAME, gameDto.getTeam1Id(), gameDto.getTeam2Id(), gameDto.getWinningTeamId(), gameDto.getManOfMatch(), gameDto.getManOfMatchFigures(), gameDto.getSeriesId());
        return jdbcTemplate.queryForObject(GET_LAST_ID, (rs, rowNum) -> {
            return rs.getInt("last_insert_id()");
        });
    }

    @Override
    public List<Game> getSeriesMatches(int id) {
        return jdbcTemplate.query(GET_SERIES_GAMES, (rs, rowNum) -> {
            return new Game(rs.getInt("game_id"), rs.getInt("team1_id"), rs.getInt("team2_id"), rs.getInt("winningTeam_id"), rs.getString("manOfMatchName"), rs.getString("manOfMatchFigures"), rs.getInt("series_id"));
        }, id);
    }

    @Override
    public List<Game> getMatchesBetween(int teamId, int teamId1) {
        return jdbcTemplate.query(GET_MATCHES_PLAYED_BETWEEN, (rs, rowNum) -> {
            return new Game(rs.getInt("game_id"), rs.getInt("team1_id"), rs.getInt("team2_id"), rs.getInt("winningTeam_id"), rs.getString("manOfMatchName"), rs.getString("manOfMatchFigures"), rs.getInt("series_id"));
        }, teamId, teamId1, teamId1, teamId);
    }

    public int getWinningTeamId(int id) {
        return jdbcTemplate.queryForObject(GET_WINNING_TEAM_ID, (rs, rowNum) -> {
            return rs.getInt("winningTeam_id");
        }, id);
    }

    public int getTeamId(String name) {
        return jdbcTemplate.queryForObject("select team_id from Team where name=?", (rs, rowNum) -> {
            return rs.getInt("team_id");
        }, name);
    }

    public String getTeamName(int id) {
        return jdbcTemplate.queryForObject("select name from Team where team_id=?", (rs, rowNum) -> {
            return rs.getString("name");
        }, id);
    }
}
