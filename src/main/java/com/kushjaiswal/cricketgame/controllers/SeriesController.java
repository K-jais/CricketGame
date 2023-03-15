package com.kushjaiswal.cricketgame.controllers;

import com.kushjaiswal.cricketgame.dto.SeriesAutomationDto;
import com.kushjaiswal.cricketgame.models.Series;
import com.kushjaiswal.cricketgame.service.series.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {
    @Autowired
    SeriesService seriesService;
    @PostMapping
    public String seriesSimulate(@RequestBody SeriesAutomationDto seriesSimulationDto) {
        seriesService.seriesSimulate(seriesSimulationDto);
        return "Series Simulated Successfully";
    }
    @GetMapping("/id/{seriesId}")
    public Series getById(@PathVariable("seriesId") int id) {
        return seriesService.getById(id);
    }

    @GetMapping("/sponsor/{sponsor}")
    public List<Series> getSeriesBySponsor(@PathVariable("sponsor") String sponsor) {
        return seriesService.getSeriesBySponsor(sponsor);
    }

    @GetMapping("/team/{teamId}")
    public List<Series> getSeriesByTeam(@PathVariable("teamId") int id) {
        return seriesService.getSeriesByTeam(id);
    }

    @GetMapping("/{team1Id}/team/{team2Id}")
    public List<Series> getSeriesBetweenTeams(@PathVariable("team1Id") int id1, @PathVariable("team2Id") int id2) {
        return seriesService.getSeriesBetweenTeams(id1, id2);
    }

}
