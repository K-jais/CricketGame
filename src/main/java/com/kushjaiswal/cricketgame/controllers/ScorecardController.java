package com.kushjaiswal.cricketgame.controllers;

import com.kushjaiswal.cricketgame.models.Inning;
import com.kushjaiswal.cricketgame.models.Scorecard;
import com.kushjaiswal.cricketgame.service.scorecard.ScorecardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scorecard")
public class ScorecardController {
    @Autowired
    ScorecardService scorecardService;

    @GetMapping("/id/{scorecardId}")
    public Scorecard getById(@PathVariable("scorecardId") int id) {
        return scorecardService.getById(id);
    }

    @GetMapping("/firstInning/{gameId}")
    public Inning getFirstInnings(@PathVariable("gameId") int id) {
        return scorecardService.getFirstInnings(id);
    }

    @GetMapping("/secondInning/{gameId}")
    public Inning getSecondInnings(@PathVariable("gameId") int id) {
        return scorecardService.getSecondInnings(id);
    }
}
