package com.kushjaiswal.cricketgame.service.inning.impl;

import com.kushjaiswal.cricketgame.models.BatsmanFigures;
import com.kushjaiswal.cricketgame.models.BowlerFigures;
import com.kushjaiswal.cricketgame.models.Inning;
import com.kushjaiswal.cricketgame.repository.inning.InningRepository;
import com.kushjaiswal.cricketgame.service.inning.InningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InningServiceImpl implements InningService {
    @Autowired
    InningRepository inningRespository;

    @Override
    public Inning getById(int id) {
        return inningRespository.getById(id);
    }

    @Override
    public String getHighestScorer(int id) {
        return inningRespository.getHighestScorer(id);
    }

    @Override
    public String getHighestWicketTaker(int id) {
        return inningRespository.getHighestWicketTaker(id);
    }

    @Override
    public List<BatsmanFigures> getBatsmanFigures(int id) {
        return inningRespository.getBatsmanFigures(id);
    }

    @Override
    public List<BowlerFigures> getBowlerFigures(int id) {
        return inningRespository.getBowlerFigures(id);
    }
}
