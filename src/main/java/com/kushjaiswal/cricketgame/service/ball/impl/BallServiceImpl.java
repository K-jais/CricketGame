package com.kushjaiswal.cricketgame.service.ball.impl;

import com.kushjaiswal.cricketgame.models.Ball;
import com.kushjaiswal.cricketgame.repository.ball.BallRepository;
import com.kushjaiswal.cricketgame.service.ball.BallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BallServiceImpl implements BallService {
    @Autowired
    BallRepository ballRepository;

    @Override
    public Ball getBallById(int id) {
        return ballRepository.getById(id);
    }

    @Override
    public List<Ball> getOverBalls(int id) {
        return ballRepository.getOverBalls(id);
    }
}
