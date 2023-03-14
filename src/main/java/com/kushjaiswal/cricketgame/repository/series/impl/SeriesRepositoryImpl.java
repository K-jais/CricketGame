package com.kushjaiswal.cricketgame.repository.series.impl;

import com.kushjaiswal.cricketgame.dto.SeriesAutomationDto;
import com.kushjaiswal.cricketgame.models.Series;
import com.kushjaiswal.cricketgame.repository.series.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeriesRepositoryImpl implements SeriesRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_SERIES_BY_ID = "select * from Series where series_id=?";
    private static final String GET_SERIES_BY_TEAM = "select * from Series where team1_id=? or team2_id=?";
    private static final String GET_SERIES_BY_SPONSOR = "select * from Series where sponsor=?";
    private static final String GET_SERIES_BETWEEN_TEAMS = "select * from Series where (team1_id=? and team2_id=?) or (team1_id=? and team2_id=?)";
    private static final String ADD_SERIES = "insert into Series(team1_id,team2_id,totalOvers,totalGames,sponsor) values(?,?,?,?,?)";
    private static final String GET_LAST_ID = "select last_insert_id()";
    private static final String INCREASE_TEAM1_WINS = "update Series set team1_wins=team1_wins+1 where series_id=?";
    private static final String INCREASE_TEAM2_WINS = "update Series set team2_wins=team2_wins+1 where series_id=?";


    @Override
    public Series getById(int id) {
        return jdbcTemplate.queryForObject(GET_SERIES_BY_ID, (rs, rowNum) -> {
            return new Series(rs.getInt("series_id"), rs.getInt("team1_id"), rs.getInt("team2_id"), rs.getInt("totalOvers"), rs.getInt("totalGames"), rs.getString("sponsor"), rs.getInt("team1_wins"), rs.getInt("team2_wins"));
        }, id);
    }

    @Override
    public List<Series> getSeriesBySponsor(String sponsor) {
        return jdbcTemplate.query(GET_SERIES_BY_SPONSOR, (rs, rowNum) -> {
            return new Series(rs.getInt("series_id"), rs.getInt("team1_id"), rs.getInt("team2_id"), rs.getInt("totalOvers"), rs.getInt("totalGames"), rs.getString("sponsor"), rs.getInt("team1_wins"), rs.getInt("team2_wins"));
        }, sponsor);
    }

    @Override
    public List<Series> getSeriesByTeam(int id) {
        return jdbcTemplate.query(GET_SERIES_BY_TEAM, (rs, rowNum) -> {
            return new Series(rs.getInt("series_id"), rs.getInt("team1_id"), rs.getInt("team2_id"), rs.getInt("totalOvers"), rs.getInt("totalGames"), rs.getString("sponsor"), rs.getInt("team1_wins"), rs.getInt("team2_wins"));
        }, id, id);
    }

    @Override
    public List<Series> getSeriesBetweenTeams(int id1, int id2) {
        return jdbcTemplate.query(GET_SERIES_BETWEEN_TEAMS, (rs, rowNum) -> {
            return new Series(rs.getInt("series_id"), rs.getInt("team1_id"), rs.getInt("team2_id"), rs.getInt("totalOvers"), rs.getInt("totalGames"), rs.getString("sponsor"), rs.getInt("team1_wins"), rs.getInt("team2_wins"));
        }, id1, id2, id1, id2);
    }

    @Override
    public int addSeries(SeriesAutomationDto seriesSimulationDto) {
        jdbcTemplate.update(ADD_SERIES, seriesSimulationDto.getTeam1Id(), seriesSimulationDto.getTeam2Id(), seriesSimulationDto.getTotalOvers(), seriesSimulationDto.getTotalGames(), seriesSimulationDto.getSponsor());
        return jdbcTemplate.queryForObject(GET_LAST_ID, (rs, rowNum) -> {
            return rs.getInt("last_insert_id()");
        });
    }

    @Override
    public void increaseTeam1Wins(int series) {
        jdbcTemplate.update(INCREASE_TEAM1_WINS, series);
    }

    @Override
    public void increaseTeam2Wins(int series) {
        jdbcTemplate.update(INCREASE_TEAM2_WINS, series);
    }
}
