package com.kushjaiswal.cricketgame.repository.batsmanfigures.impl;

import com.kushjaiswal.cricketgame.dto.BatsmanFiguresDto;
import com.kushjaiswal.cricketgame.repository.batsmanfigures.BatsmanFiguresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BatsmanFiguresRepositoryImpl implements BatsmanFiguresRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String ADD_BATSMANFIGURES = "insert into BatsmanFigures(runs,balls,inning_id,notout,position) values(?,?,?,?,?)";

    @Override
    public void addBatsmanFigures(BatsmanFiguresDto batsmanFiguresDto) {
        jdbcTemplate.update(ADD_BATSMANFIGURES, batsmanFiguresDto.getRuns(), batsmanFiguresDto.getBalls(), batsmanFiguresDto.getInningId(), batsmanFiguresDto.isNotout(), batsmanFiguresDto.getPosition());
    }
}
