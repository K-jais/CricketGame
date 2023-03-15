package com.kushjaiswal.cricketgame.service.series.impl;

import com.kushjaiswal.cricketgame.dto.SeriesAutomationDto;
import com.kushjaiswal.cricketgame.models.Series;
import com.kushjaiswal.cricketgame.repository.series.SeriesRepository;
import com.kushjaiswal.cricketgame.service.series.SeriesService;
import com.kushjaiswal.cricketgame.utils.SeriesSimulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService {
    @Autowired
    SeriesRepository seriesRepository;
    @Autowired
    SeriesSimulation seriesSimulation;

    @Override
    public Series getById(int id) {
        return seriesRepository.getById(id);
    }

    @Override
    public List<Series> getSeriesBySponsor(String sponsor) {
        return seriesRepository.getSeriesBySponsor(sponsor);
    }

    @Override
    public List<Series> getSeriesByTeam(int id) {
        return seriesRepository.getSeriesByTeam(id);
    }

    @Override
    public List<Series> getSeriesBetweenTeams(int id1, int id2) {
        return seriesRepository.getSeriesBetweenTeams(id1, id2);
    }

    @Override
    public void seriesSimulate(SeriesAutomationDto seriesSimulationDto) {
        seriesSimulation.simulate(seriesSimulationDto);
    }
}
