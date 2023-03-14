package com.kushjaiswal.cricketgame.repository.over;

import com.kushjaiswal.cricketgame.dto.BowlerOverDto;
import com.kushjaiswal.cricketgame.dto.OverDto;
import com.kushjaiswal.cricketgame.models.Overball;

import java.util.List;

public interface OverRepository {
    Overball getById(int id);

    List<Overball> getBowlerOvers(BowlerOverDto bowlerOverDto);

    int addOver(OverDto overDto);

    List<Overball> getOversInning(int inningId);
}
