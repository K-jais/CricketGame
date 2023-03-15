package com.kushjaiswal.cricketgame.service.over.impl;

import com.kushjaiswal.cricketgame.dto.BowlerOverDto;
import com.kushjaiswal.cricketgame.models.Overball;
import com.kushjaiswal.cricketgame.repository.over.OverRepository;
import com.kushjaiswal.cricketgame.service.over.OverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverServiceImpl implements OverService {
    @Autowired
    OverRepository overRepository;

    @Override
    public Overball getById(int id) {
        return overRepository.getById(id);
    }

    @Override
    public List<Overball> getBowlerOvers(BowlerOverDto bowlerOverDto) {
        return overRepository.getBowlerOvers(bowlerOverDto);
    }
}
