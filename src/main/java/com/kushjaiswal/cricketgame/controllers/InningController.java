package com.kushjaiswal.cricketgame.controllers;

import com.kushjaiswal.cricketgame.models.BatsmanFigures;
import com.kushjaiswal.cricketgame.models.BowlerFigures;
import com.kushjaiswal.cricketgame.models.Inning;
import com.kushjaiswal.cricketgame.service.inning.InningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inning")
public class InningController {
    @Autowired
    InningService inningService;

    @GetMapping("/id/{inningId}")
    public Inning getById(@PathVariable("inningId") int id) {
        return inningService.getById(id);
    }

    @GetMapping("/highestScorer/{inningId}")
    public String getHighestScorer(@PathVariable("inningId") int id) {
        return inningService.getHighestScorer(id);
    }

    @GetMapping("/highestWicketTaker/{inningId}")
    public String getHighestWicketTaker(@PathVariable("inningId") int id) {
        return inningService.getHighestWicketTaker(id);
    }

    @GetMapping("/batsmanfigure/{inningId}")
    public List<BatsmanFigures> getBatsmanFigures(@PathVariable("inningId") int id) {
        return inningService.getBatsmanFigures(id);
    }

    @GetMapping("/bowlerfigure/{inningId}")
    public List<BowlerFigures> getBowlerFigures(@PathVariable("inningId") int id) {
        return inningService.getBowlerFigures(id);
    }
}
