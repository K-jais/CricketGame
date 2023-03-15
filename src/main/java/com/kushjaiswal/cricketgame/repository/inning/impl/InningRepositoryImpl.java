package com.kushjaiswal.cricketgame.repository.inning.impl;

import com.kushjaiswal.cricketgame.dto.InningDto;
import com.kushjaiswal.cricketgame.models.BatsmanFigures;
import com.kushjaiswal.cricketgame.models.BowlerFigures;
import com.kushjaiswal.cricketgame.models.Inning;
import com.kushjaiswal.cricketgame.repository.inning.InningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InningRepositoryImpl implements InningRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_INNING_BY_ID = "select * from Inning where inning_id=?";
    private static final String GET_HIGHEST_SCORER = "select highestScorerName,highestScorerRuns,highestScorerBalls from Inning where inning_id=?";
    private static final String GET_HIGHEST_WICKET_TAKER = "select highestWicketTakerName,highestWicketTakerWickets,highestWicketTakerRunsGiven from Inning where inning_id=?";
    private static final String GET_BATSMAN_FIGURES = "select * from BatsmanFigures where inning_id=? order by position";
    private static final String GET_BOWLER_FIGURES = "select * from BowlerFigures where inning_id=? order by position";
    private static final String GET_INNING_SCORE = "select totalRunsScored from Inning where inning_id=?";
    private static final String ADD_INNING = "insert into Inning(totalOvers,winToss,teamName,totalRunsScored,totalOversBowled,totalWicketsGone,extras,highestScorerName,highestScorerRuns,highestScorerBalls,highestWicketTakerName,highestWicketTakerWickets,highestWicketTakerRunsGiven) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_LAST_ID = "select last_insert_id()";

    @Override
    public Inning getById(int id) {
        return jdbcTemplate.queryForObject(GET_INNING_BY_ID, (rs, rowNum) -> {
            return new Inning(rs.getInt("inning_id"), rs.getInt("totalOvers"), rs.getBoolean("winToss"), rs.getString("teamName"), rs.getInt("totalRunsScored"), rs.getDouble("totalOversBowled"), rs.getInt("totalWicketsGone"), rs.getInt("extras"), rs.getString("highestScorerName"), rs.getInt("highestScorerRuns"), rs.getInt("highestScorerBalls"), rs.getString("highestWicketTakerName"), rs.getInt("highestWicketTakerWickets"), rs.getInt("highestWicketTakerRunsGiven"));
        }, id);
    }

    @Override
    public String getHighestScorer(int id) {
        return jdbcTemplate.queryForObject(GET_HIGHEST_SCORER, (rs, rowNum) -> {
            return rs.getString("highestScorerName") + " " + rs.getInt("highestScorerRuns") + " runs in " + rs.getInt("highestScorerBalls") + " balls";
        }, id);
    }

    @Override
    public String getHighestWicketTaker(int id) {
        return jdbcTemplate.queryForObject(GET_HIGHEST_WICKET_TAKER, (rs, rowNum) -> {
            return rs.getString("highestWicketTakerName") + " " + rs.getInt("highestWicketTakerWickets") + "/" + rs.getInt("highestWicketTakerRunsGiven");
        }, id);
    }

    @Override
    public List<BatsmanFigures> getBatsmanFigures(int id) {
        return jdbcTemplate.query(GET_BATSMAN_FIGURES, (rs, rowNum) -> {
            return new BatsmanFigures(rs.getInt("batsFig_id"), rs.getInt("runs"), rs.getInt("balls"), rs.getInt("inning_id"), rs.getBoolean("notout"), rs.getInt("position"));
        }, id);
    }

    @Override
    public List<BowlerFigures> getBowlerFigures(int id) {
        return jdbcTemplate.query(GET_BOWLER_FIGURES, (rs, rowNum) -> {
            return new BowlerFigures(rs.getInt("bowlsFig_id"), rs.getInt("wickets"), rs.getInt("runsGiven"), rs.getInt("inning_id"), rs.getInt("position"));
        }, id);
    }

    @Override
    public int getInningScore(int id) {
        return jdbcTemplate.queryForObject(GET_INNING_SCORE, (rs, rowNum) -> {
            return rs.getInt("totalRunsScored");
        }, id);
    }

    @Override
    public int addInning(InningDto inningDto) {
        jdbcTemplate.update(ADD_INNING, inningDto.getTotalOvers(), inningDto.isWinToss(), inningDto.getTeamName(), inningDto.getTotalRunsScored(), inningDto.getTotalOversBowled(), inningDto.getTotalWicketsGone(), inningDto.getExtras(), inningDto.getHighestScorer(), inningDto.getHighestScorerRuns(), inningDto.getHighestScorerBalls(), inningDto.getHighestWicketTaker(), inningDto.getHighestWicketTakerWickets(), inningDto.getHighestWicketTakerRunsGiven());
        return jdbcTemplate.queryForObject(GET_LAST_ID, (rs, rowNum) -> {
            return rs.getInt("last_insert_id()");
        });
    }
}
