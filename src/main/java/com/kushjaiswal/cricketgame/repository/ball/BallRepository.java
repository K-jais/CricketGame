package com.kushjaiswal.cricketgame.repository.ball;

import com.kushjaiswal.cricketgame.dto.BallDto;
import com.kushjaiswal.cricketgame.models.Ball;

import java.util.List;

public interface BallRepository {
    Ball getById(int id);

    List<Ball> getOverBalls(int id);

    void addBall(BallDto balldto);
}
