package com.kushjaiswal.cricketgame.repository.ball.impl;

import com.kushjaiswal.cricketgame.dto.BallDto;
import com.kushjaiswal.cricketgame.dto.PlayerDto;
import com.kushjaiswal.cricketgame.models.Ball;
import com.kushjaiswal.cricketgame.repository.ball.BallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BallRepositoryImpl implements BallRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_BALL_BY_ID = "select * from Ball where ball_id=?";
    private static final String GET_OVER_BALLS = "select * from Ball where over_id=?";
    private static final String ADD_BALL = "insert into Ball(batsmanOnStrike,eventHappened,over_id) values(?,?,?)";

    @Override
    public Ball getById(int id) {
        return jdbcTemplate.queryForObject(GET_BALL_BY_ID, (rs, rowNum) -> {
            return new Ball(rs.getInt("ball_id"), rs.getString("batsmanOnStrike"), rs.getString("eventHappened"), rs.getInt("over_id"));
        }, id);
    }

    @Override
    public List<Ball> getOverBalls(int id) {
        return jdbcTemplate.query(GET_OVER_BALLS, (rs, rowNum) -> {
            return new Ball(rs.getInt("ball_id"), rs.getString("batsmanOnStrike"), rs.getString("eventHappened"), rs.getInt("over_id"));
        }, id);
    }

    @Override
    public void addBall(BallDto ballDto) {
        jdbcTemplate.update(ADD_BALL, ballDto.getBatsmanOnStrike(), ballDto.getResult(), ballDto.getOverId());
    }
}
