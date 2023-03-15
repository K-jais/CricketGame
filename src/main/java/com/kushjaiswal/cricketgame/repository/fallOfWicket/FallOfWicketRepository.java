package com.kushjaiswal.cricketgame.repository.fallOfWicket;

import com.kushjaiswal.cricketgame.dto.FallOfWicketDto;
import com.kushjaiswal.cricketgame.models.FallOfWicket;

import java.util.List;

public interface FallOfWicketRepository {
    FallOfWicket getById(int id);

    List<FallOfWicket> getFallOfWicketsInInnings(int id);

    void addFallOfWicket(FallOfWicketDto fallOfWicketDto);
}
