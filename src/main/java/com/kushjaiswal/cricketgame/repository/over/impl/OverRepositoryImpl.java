package com.kushjaiswal.cricketgame.repository.over.impl;

import com.kushjaiswal.cricketgame.dto.BowlerOverDto;
import com.kushjaiswal.cricketgame.dto.OverDto;
import com.kushjaiswal.cricketgame.models.Overball;
import com.kushjaiswal.cricketgame.repository.over.OverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OverRepositoryImpl implements OverRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String GET_OVER_BY_ID = "select * from Overball where over_id=?";
    private static final String GET_OVERS_OF_BOWLER = "select * from Overball where inning_id=? and bowlerName=? order by over_id";
    private static final String ADD_OVER = "insert into Overball(bowlerName,inning_id) values(?,?)";
    private static final String GET_INNING_OVERS = "select * from Overball where inning_id=?";
    private static final String GET_LAST_ID = "select last_insert_id()";

    @Override
    public Overball getById(int id) {
        return jdbcTemplate.queryForObject(GET_OVER_BY_ID, (rs, rowNum) -> {
            return new Overball(rs.getInt("over_id"), rs.getString("bowlerName"), rs.getInt("inning_id"));
        }, id);
    }

    @Override
    public List<Overball> getBowlerOvers(BowlerOverDto bowlerOverDto) {
        return jdbcTemplate.query(GET_OVERS_OF_BOWLER, (rs, rowNum) -> {
            return new Overball(rs.getInt("over_id"), rs.getString("bowlerName"), rs.getInt("inning_id"));
        }, bowlerOverDto.getInningId(), bowlerOverDto.getBowlerName());
    }

    @Override
    public int addOver(OverDto overDto) {
        jdbcTemplate.update(ADD_OVER, overDto.getBowlerName(), overDto.getInningId());
        return jdbcTemplate.queryForObject(GET_LAST_ID, (rs, rowNum) -> {
            return rs.getInt("last_insert_id()");
        });
    }

    @Override
    public List<Overball> getOversInning(int inningId) {
        return jdbcTemplate.query(GET_INNING_OVERS, (rs, rowNum) -> {
            return new Overball(rs.getInt("over_id"), rs.getString("bowlerName"), rs.getInt("inning_id"));
        }, inningId);
    }
}
