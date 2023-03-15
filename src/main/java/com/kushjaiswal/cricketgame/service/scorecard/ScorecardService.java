package com.kushjaiswal.cricketgame.service.scorecard;

import com.kushjaiswal.cricketgame.models.Inning;
import com.kushjaiswal.cricketgame.models.Scorecard;

public interface ScorecardService {
    Scorecard getById(int id);

    Inning getFirstInnings(int id);

    Inning getSecondInnings(int id);
}
