package com.kushjaiswal.cricketgame.repository.scorecard;

import com.kushjaiswal.cricketgame.dto.ScorecardDto;
import com.kushjaiswal.cricketgame.models.Inning;
import com.kushjaiswal.cricketgame.models.Scorecard;

public interface ScorecardRepository {
    Scorecard getById(int id);

    Inning getFirstInnings(int id);

    Inning getSecondInnings(int id);

    void addScorecard(ScorecardDto scorecardDto);

    Scorecard getByGameId(int id);
}
