package com.kushjaiswal.cricketgame.repository.player.impl;

import com.kushjaiswal.cricketgame.dto.BatsmanFiguresDto;
import com.kushjaiswal.cricketgame.dto.BowlerFiguresDto;
import com.kushjaiswal.cricketgame.dto.PlayerDto;
import com.kushjaiswal.cricketgame.models.Player;
import com.kushjaiswal.cricketgame.repository.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String GET_PLAYER_ID = "select * from Player where player_id=?";
    private static final String ADD_PLAYER = "insert into Player(name,age,runsScored,wicketsTaken,numberOfCenturies,fiveWicketHalls,battingBehaviour,bowlingBehaviour,position,team_id) values(?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_PLAYER = "update Player set name=?,age=?,runsScored=?,wicketsTaken=?,numberOfCenturies=?,fiveWicketHalls=?,battingBehaviour=?,bowlingBehaviour=?,position=?,team_id=? where player_id=?";
    private static final String DELETE_PLAYER = "delete from Player where player_id=?";
    private static final String GET_PLAYERS = "select * from Player where team_id=? order by position";

    private static final String INCREASE_RUNS = "update Player set runsScored=runsScored+? where team_id=? and position=?";
    private static final String INCREASE_WICKETS = "update Player set wicketsTaken=wicketsTaken+? where team_id=? and position=?";
    private static final String INCREASE_CENTURY = "update Player set numberOfCenturies=numberOfCenturies+1 where team_id=? and position=?";
    private static final String INCREASE_FIVEWICKET = "update Player set fiveWicketHalls=fiveWicketHalls+1 where team_id=? and position=?";

    @Override
    public Player getById(int id) {
        return jdbcTemplate.queryForObject(GET_PLAYER_ID, (rs, rowNum) -> {
            return new Player(rs.getInt("player_id"), rs.getString("name"), rs.getInt("age"), rs.getInt("runsScored"), rs.getInt("wicketsTaken"), rs.getInt("numberOfCenturies"), rs.getInt("fiveWicketHalls"), Player.BattingBehaviour.valueOf(rs.getString("battingBehaviour")), Player.BowlingBehaviour.valueOf(rs.getString("bowlingBehaviour")), rs.getInt("position"), rs.getInt("team_id"));
        }, id);
    }

    @Override
    public PlayerDto addPlayer(PlayerDto playerDto) {
        jdbcTemplate.update(ADD_PLAYER, playerDto.getName(), playerDto.getAge(), playerDto.getRunsScored(), playerDto.getWicketsTaken(), playerDto.getNumberOfCenturies(), playerDto.getFiveWicketHalls(), playerDto.getBattingBehaviour(), playerDto.getBowlingBehaviour(), playerDto.getPosition(), playerDto.getTeamId());
        return playerDto;
    }

    @Override
    public PlayerDto updatePlayer(PlayerDto playerDto, int id) {
        jdbcTemplate.update(UPDATE_PLAYER, playerDto.getName(), playerDto.getAge(), playerDto.getRunsScored(), playerDto.getWicketsTaken(), playerDto.getNumberOfCenturies(), playerDto.getFiveWicketHalls(), playerDto.getBattingBehaviour(), playerDto.getBowlingBehaviour(), playerDto.getPosition(), playerDto.getTeamId(), id);
        return playerDto;
    }

    @Override
    public void deletePlayer(int id) {
        jdbcTemplate.update(DELETE_PLAYER, id);
    }

    @Override
    public List<Player> getPlayersOfTeam(int id) {
        return jdbcTemplate.query(GET_PLAYERS, (rs, rowNum) -> {
            return new Player(rs.getInt("player_id"), rs.getString("name"), rs.getInt("age"), rs.getInt("runsScored"), rs.getInt("wicketsTaken"), rs.getInt("numberOfCenturies"), rs.getInt("fiveWicketHalls"), Player.BattingBehaviour.valueOf(rs.getString("battingBehaviour")), Player.BowlingBehaviour.valueOf(rs.getString("bowlingBehaviour")), rs.getInt("position"), rs.getInt("team_id"));
        }, id);
    }

    @Override
    public void increaseRunsScored(int battingTeamId, BatsmanFiguresDto batsmanFiguresDto) {
        int position = batsmanFiguresDto.getPosition();
        jdbcTemplate.update(INCREASE_RUNS, batsmanFiguresDto.getRuns(), battingTeamId, position);
        if (batsmanFiguresDto.getRuns() >= 100) {
            jdbcTemplate.update(INCREASE_CENTURY, battingTeamId, position);
        }
    }

    @Override
    public void increaseWicketsTaken(int bowlingTeamId, BowlerFiguresDto bowlerFiguresDto) {
        int position = bowlerFiguresDto.getPosition();
        jdbcTemplate.update(INCREASE_WICKETS, bowlerFiguresDto.getWickets(), bowlingTeamId, position);
        if (bowlerFiguresDto.getWickets() >= 5) {
            jdbcTemplate.update(INCREASE_FIVEWICKET, bowlingTeamId, position);
        }
    }
}
