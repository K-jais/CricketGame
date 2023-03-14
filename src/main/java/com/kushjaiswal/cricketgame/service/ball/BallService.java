package com.kushjaiswal.cricketgame.service.ball;

import com.kushjaiswal.cricketgame.models.Ball;

import java.util.List;

public interface BallService {
    Ball getBallById(int id);

    List<Ball> getOverBalls(int id);
}
