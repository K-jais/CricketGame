package com.kushjaiswal.cricketgame.service.displaymodels;

import com.kushjaiswal.cricketgame.displayModels.*;

public interface DisplayModelsService {
    SmallScorecard getScorecardMatch(int id);

    SeriesScorecard getScorecardSeries(int id);

    HighestScorerSeries getHighestScorer(int id);

    HighestWicketTakerSeries getHighestWicketTaker(int id);

    PlayerRecord getPlayerRecord(int id, int id1);

    OverScorecard getOverScorecard(int id);

    OverBowler getOverBowler(int id, int id1);

    PlayerRecordAgainst getPlayerRecordAgainst(int id, int id1);
}
