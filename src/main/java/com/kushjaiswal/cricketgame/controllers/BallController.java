package com.kushjaiswal.cricketgame.controllers;

import java.util.*;

import com.kushjaiswal.cricketgame.models.Ball;
import com.kushjaiswal.cricketgame.service.ball.BallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ball")
public class BallController {
    @Autowired
    BallService ballService;

    @GetMapping("/id/{ballId}")
    public Ball getById(@PathVariable("ballId") int id) {
        return ballService.getBallById(id);
    }

    @GetMapping("/overs/{overId}")
    public List<Ball> getBalls(@PathVariable("overId") int id) {
        return ballService.getOverBalls(id);
    }

}
