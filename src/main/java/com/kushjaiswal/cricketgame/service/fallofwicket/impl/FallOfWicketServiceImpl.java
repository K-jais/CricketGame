package com.kushjaiswal.cricketgame.service.fallofwicket.impl;

import com.kushjaiswal.cricketgame.models.FallOfWicket;
import com.kushjaiswal.cricketgame.repository.fallOfWicket.FallOfWicketRepository;
import com.kushjaiswal.cricketgame.service.fallofwicket.FallOfWicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FallOfWicketServiceImpl implements FallOfWicketService {
    @Autowired
    FallOfWicketRepository fallOfWicketRepository;

    @Override
    public FallOfWicket getById(int id) {
        return fallOfWicketRepository.getById(id);
    }

    @Override
    public List<FallOfWicket> getInningFallOfWickets(int id) {
        return fallOfWicketRepository.getFallOfWicketsInInnings(id);
    }
}
