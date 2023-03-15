package com.kushjaiswal.cricketgame.service.inning;

import com.kushjaiswal.cricketgame.models.BatsmanFigures;
import com.kushjaiswal.cricketgame.models.BowlerFigures;
import com.kushjaiswal.cricketgame.models.Inning;

import java.util.List;

public interface InningService {
    Inning getById(int id);

    String getHighestScorer(int id);

    String getHighestWicketTaker(int id);

    List<BatsmanFigures> getBatsmanFigures(int id);

    List<BowlerFigures> getBowlerFigures(int id);
}
