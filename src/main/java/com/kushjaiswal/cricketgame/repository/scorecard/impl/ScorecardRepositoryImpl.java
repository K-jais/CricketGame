package com.kushjaiswal.cricketgame.repository.scorecard.impl;

import com.kushjaiswal.cricketgame.dto.ScorecardDto;
import com.kushjaiswal.cricketgame.models.Inning;
import com.kushjaiswal.cricketgame.models.Scorecard;
import com.kushjaiswal.cricketgame.repository.scorecard.ScorecardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScorecardRepositoryImpl implements ScorecardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_SCORECARD_ID = "select * from Scorecard where scorecard_id=?";
    private static final String GET_FIRST_INNING_ID = "select inning1_id from Scorecard where game_id=?";
    private static final String GET_SECOND_INNING_ID = "select inning2_id from Scorecard where game_id=?";
    private static final String GET_INNING = "select * from Inning where inning_id=?";
    private static final String ADD_SCORECARD = "insert into Scorecard(inning1_id,inning2_id,game_id) values(?,?,?)";

    private static final String GET_SCORECARD_BY_GAMEID = "select * from Scorecard where game_id=?";

    @Override
    public Scorecard getById(int id) {
        return jdbcTemplate.queryForObject(GET_SCORECARD_ID, (rs, rowNum) -> {
            return new Scorecard(rs.getInt("scorecard_id"), rs.getInt("inning1_id"), rs.getInt("inning2_id"), rs.getInt("game_id"));
        }, id);
    }

    @Override
    public Inning getFirstInnings(int id) {
        int inningId = getFirstInningId(id);
        return jdbcTemplate.queryForObject(GET_INNING, (rs, rowNum) -> {
            return new Inning(rs.getInt("inning_id"), rs.getInt("totalOvers"), rs.getBoolean("winToss"), rs.getString("teamName"), rs.getInt("totalRunsScored"), rs.getDouble("totalOversBowled"), rs.getInt("totalWicketsGone"), rs.getInt("extras"), rs.getString("highestScorerName"), rs.getInt("highestScorerRuns"), rs.getInt("highestScorerBalls"), rs.getString("highestWicketTakerName"), rs.getInt("highestWicketTakerWickets"), rs.getInt("highestWicketTakerRunsGiven"));
        }, inningId);
    }

    @Override
    public Inning getSecondInnings(int id) {
        int inningId = getSecondInningId(id);
        return jdbcTemplate.queryForObject(GET_INNING, (rs, rowNum) -> {
            return new Inning(rs.getInt("inning_id"), rs.getInt("totalOvers"), rs.getBoolean("winToss"), rs.getString("teamName"), rs.getInt("totalRunsScored"), rs.getDouble("totalOversBowled"), rs.getInt("totalWicketsGone"), rs.getInt("extras"), rs.getString("highestScorerName"), rs.getInt("highestScorerRuns"), rs.getInt("highestScorerBalls"), rs.getString("highestWicketTakerName"), rs.getInt("highestWicketTakerWickets"), rs.getInt("highestWicketTakerRunsGiven"));
        }, inningId);
    }

    @Override
    public void addScorecard(ScorecardDto scorecardDto) {
        jdbcTemplate.update(ADD_SCORECARD, scorecardDto.getInning1Id(), scorecardDto.getInning2Id(), scorecardDto.getGameId());
    }

    @Override
    public Scorecard getByGameId(int id) {
        return jdbcTemplate.queryForObject(GET_SCORECARD_BY_GAMEID, (rs, rowNum) -> {
            return new Scorecard(rs.getInt("scorecard_id"), rs.getInt("inning1_id"), rs.getInt("inning2_id"), rs.getInt("game_id"));
        }, id);
    }

    public int getFirstInningId(int id) {
        return jdbcTemplate.queryForObject(GET_FIRST_INNING_ID, (rs, rowNum) -> {
            return rs.getInt("inning1_id");
        }, id);
    }

    public int getSecondInningId(int id) {
        return jdbcTemplate.queryForObject(GET_SECOND_INNING_ID, (rs, rowNum) -> {
            return rs.getInt("inning2_id");
        }, id);
    }
}
