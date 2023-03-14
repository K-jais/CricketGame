package com.kushjaiswal.cricketgame.service.series;

import com.kushjaiswal.cricketgame.dto.SeriesAutomationDto;
import com.kushjaiswal.cricketgame.models.Series;

import java.util.List;

public interface SeriesService {

    Series getById(int id);

    List<Series> getSeriesBySponsor(String sponsor);

    List<Series> getSeriesByTeam(int id);

    List<Series> getSeriesBetweenTeams(int id1, int id2);

    void seriesSimulate(SeriesAutomationDto seriesSimulationDto);
}
