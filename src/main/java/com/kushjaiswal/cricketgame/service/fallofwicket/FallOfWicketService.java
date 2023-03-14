package com.kushjaiswal.cricketgame.service.fallofwicket;

import com.kushjaiswal.cricketgame.models.FallOfWicket;

import java.util.List;

public interface FallOfWicketService {
    FallOfWicket getById(int id);

    List<FallOfWicket> getInningFallOfWickets(int id);
}
