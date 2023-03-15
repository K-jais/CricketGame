package com.kushjaiswal.cricketgame.repository.series;

import com.kushjaiswal.cricketgame.dto.SeriesAutomationDto;
import com.kushjaiswal.cricketgame.models.Series;

import java.util.List;

public interface SeriesRepository {
    Series getById(int id);

    List<Series> getSeriesBySponsor(String sponsor);

    List<Series> getSeriesByTeam(int id);

    List<Series> getSeriesBetweenTeams(int id1, int id2);

    int addSeries(SeriesAutomationDto seriesSimulationDto);

    void increaseTeam1Wins(int series);

    void increaseTeam2Wins(int series);
}
