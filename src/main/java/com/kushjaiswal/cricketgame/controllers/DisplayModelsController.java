package com.kushjaiswal.cricketgame.controllers;

import com.kushjaiswal.cricketgame.displayModels.*;
import com.kushjaiswal.cricketgame.service.displaymodels.DisplayModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/display")
public class DisplayModelsController {
    @Autowired
    DisplayModelsService displayModelsService;

    @GetMapping("/scorecardMatch/{gameId}")
    public SmallScorecard getScorecardMatch(@PathVariable("gameId") int id) {
        return displayModelsService.getScorecardMatch(id);
    }

    @GetMapping("/scorecardSeries/{seriesId}")
    public SeriesScorecard getScorecardSeries(@PathVariable("seriesId") int id) {
        return displayModelsService.getScorecardSeries(id);
    }

    @GetMapping("/highestScorer/{seriesId}")
    public HighestScorerSeries getHighestScorer(@PathVariable("seriesId") int id) {
        return displayModelsService.getHighestScorer(id);
    }

    @GetMapping("/highestWicketTaker/{seriesId}")
    public HighestWicketTakerSeries getHighestWicketTaker(@PathVariable("seriesId") int id) {
        return displayModelsService.getHighestWicketTaker(id);
    }

    @GetMapping("/playerRecord/{playerId}/series/{seriesId}")
    public PlayerRecord getPlayerRecord(@PathVariable("seriesId") int id, @PathVariable("playerId") int id1) {
        return displayModelsService.getPlayerRecord(id, id1);
    }

    @GetMapping("/overScorecard/{gameId}")
    public OverScorecard getOverScorecard(@PathVariable("gameId") int id) {
        return displayModelsService.getOverScorecard(id);
    }

    @GetMapping("/overBowler/{gameId}/player/{playerId}")
    public OverBowler getOverBowler(@PathVariable("gameId") int id, @PathVariable("playerId") int id1) {
        return displayModelsService.getOverBowler(id, id1);
    }

    @GetMapping("/playerRecordAgainst/{playerId}/team/{teamId}")
    public PlayerRecordAgainst getPlayerRecordAgainst(@PathVariable("playerId") int id, @PathVariable("teamId") int id1) {
        return displayModelsService.getPlayerRecordAgainst(id, id1);
    }
}
