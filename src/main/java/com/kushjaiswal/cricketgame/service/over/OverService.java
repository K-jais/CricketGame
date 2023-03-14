package com.kushjaiswal.cricketgame.service.over;

import com.kushjaiswal.cricketgame.dto.BowlerOverDto;
import com.kushjaiswal.cricketgame.models.Overball;

import java.util.List;

public interface OverService {
    Overball getById(int id);

    List<Overball> getBowlerOvers(BowlerOverDto bowlerOverDto);
}
