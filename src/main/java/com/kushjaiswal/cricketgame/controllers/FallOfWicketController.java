package com.kushjaiswal.cricketgame.controllers;

import com.kushjaiswal.cricketgame.models.FallOfWicket;
import com.kushjaiswal.cricketgame.service.fallofwicket.FallOfWicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fallofwicket")
public class FallOfWicketController {
    @Autowired
    FallOfWicketService fallOfWicketService;

    @GetMapping("/id/{fallId}")
    public FallOfWicket getById(@PathVariable("fallId") int id) {
        return fallOfWicketService.getById(id);
    }

    @GetMapping("/inning/{inningId}")
    public List<FallOfWicket> getFallOfWickets(@PathVariable("inningId") int id) {
        return fallOfWicketService.getInningFallOfWickets(id);
    }
}
