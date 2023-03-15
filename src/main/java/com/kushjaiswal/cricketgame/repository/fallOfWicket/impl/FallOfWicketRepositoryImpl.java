package com.kushjaiswal.cricketgame.repository.fallOfWicket.impl;

import com.kushjaiswal.cricketgame.dto.FallOfWicketDto;
import com.kushjaiswal.cricketgame.models.FallOfWicket;
import com.kushjaiswal.cricketgame.repository.fallOfWicket.FallOfWicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FallOfWicketRepositoryImpl implements FallOfWicketRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_FALLOFWICKET_BY_ID = "select * from FallOfWicket where fallOfWicket_id=?";
    private static final String GET_INNING_FALLOFWICKETS = "select * from FallOfWicket where inning_id=? order by wicketNumber";
    private static final String ADD_FALLOFWICKET = "insert into FallOfWicket(runs,wicketNumber,inning_id) values(?,?,?)";

    @Override
    public FallOfWicket getById(int id) {
        return jdbcTemplate.queryForObject(GET_FALLOFWICKET_BY_ID, (rs, rowNum) -> {
            return new FallOfWicket(rs.getInt("fallOfWicket_id"), rs.getInt("runs"), rs.getInt("wicketNumber"), rs.getInt("inning_id"));
        }, id);
    }

    @Override
    public List<FallOfWicket> getFallOfWicketsInInnings(int id) {
        return jdbcTemplate.query(GET_INNING_FALLOFWICKETS, (rs, rowNum) -> {
            return new FallOfWicket(rs.getInt("fallOfWicket_id"), rs.getInt("runs"), rs.getInt("wicketNumber"), rs.getInt("inning_id"));
        }, id);
    }

    @Override
    public void addFallOfWicket(FallOfWicketDto fallOfWicketDto) {
        jdbcTemplate.update(ADD_FALLOFWICKET, fallOfWicketDto.getRuns(), fallOfWicketDto.getWicketNumber(), fallOfWicketDto.getInningId());
    }
}
