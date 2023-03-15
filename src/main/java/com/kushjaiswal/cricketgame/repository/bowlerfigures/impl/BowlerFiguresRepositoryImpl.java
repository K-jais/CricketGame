package com.kushjaiswal.cricketgame.repository.bowlerfigures.impl;

import com.kushjaiswal.cricketgame.dto.BowlerFiguresDto;
import com.kushjaiswal.cricketgame.repository.bowlerfigures.BowlerFiguresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BowlerFiguresRepositoryImpl implements BowlerFiguresRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String ADD_BOWLERFIGURES = "insert into BowlerFigures(wickets,runsGiven,inning_id,position) values(?,?,?,?)";

    @Override
    public void addBowlerFigures(BowlerFiguresDto bowlerFiguresDto) {
        jdbcTemplate.update(ADD_BOWLERFIGURES, bowlerFiguresDto.getWickets(), bowlerFiguresDto.getRunsGiven(), bowlerFiguresDto.getInningId(), bowlerFiguresDto.getPosition());
    }
}
