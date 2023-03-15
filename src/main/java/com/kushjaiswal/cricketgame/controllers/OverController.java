package com.kushjaiswal.cricketgame.controllers;

import com.kushjaiswal.cricketgame.dto.BowlerOverDto;
import com.kushjaiswal.cricketgame.models.Overball;
import com.kushjaiswal.cricketgame.service.over.OverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/over")
public class OverController {
    @Autowired
    OverService overService;

    @GetMapping("/id/{overId}")
    public Overball getById(@PathVariable("overId") int id) {
        return overService.getById(id);
    }

    @PostMapping("/bowler")
    public List<Overball> getBowlerOvers(@RequestBody BowlerOverDto bowlerOverDto) {
        return overService.getBowlerOvers(bowlerOverDto);
    }
}
