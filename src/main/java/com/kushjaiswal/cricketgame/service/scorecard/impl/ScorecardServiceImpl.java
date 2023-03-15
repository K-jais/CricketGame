package com.kushjaiswal.cricketgame.service.scorecard.impl;

import com.kushjaiswal.cricketgame.models.Inning;
import com.kushjaiswal.cricketgame.models.Scorecard;
import com.kushjaiswal.cricketgame.repository.scorecard.ScorecardRepository;
import com.kushjaiswal.cricketgame.service.scorecard.ScorecardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScorecardServiceImpl implements ScorecardService {
    @Autowired
    ScorecardRepository scorecardRepository;

    @Override
    public Scorecard getById(int id) {
        return scorecardRepository.getById(id);
    }

    @Override
    public Inning getFirstInnings(int id) {
        return scorecardRepository.getFirstInnings(id);
    }

    @Override
    public Inning getSecondInnings(int id) {
        return scorecardRepository.getSecondInnings(id);
    }
}
