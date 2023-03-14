package com.kushjaiswal.cricketgame.utils;

import com.kushjaiswal.cricketgame.dto.GameDto;
import com.kushjaiswal.cricketgame.dto.GameAutomationDto;
import com.kushjaiswal.cricketgame.dto.ScorecardDto;
import com.kushjaiswal.cricketgame.models.Inning;
import com.kushjaiswal.cricketgame.models.Series;
import com.kushjaiswal.cricketgame.repository.game.GameRepository;
import com.kushjaiswal.cricketgame.repository.inning.InningRepository;
import com.kushjaiswal.cricketgame.repository.scorecard.ScorecardRepository;
import com.kushjaiswal.cricketgame.repository.series.SeriesRepository;
import com.kushjaiswal.cricketgame.repository.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameSimulation {
    @Autowired
    InningRepository inningRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    ScorecardRepository scorecardRepository;
    @Autowired
    InningSimulation firstInningsSimulation;
    @Autowired
    InningSimulation secondInningsSimulation;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    SeriesRepository seriesRepository;

    public void simulate(GameAutomationDto gameSimulationDto) {
        int battingTeamId = gameSimulationDto.getBattingTeamId();
        int bowlingTeamId = gameSimulationDto.getBowlingTeamId();
        int totalOvers = gameSimulationDto.getTotalOvers();
        GameDto gameDto = new GameDto();
        gameDto.setTeam1Id(battingTeamId);
        gameDto.setTeam2Id(bowlingTeamId);
        gameDto.setSeriesId(gameSimulationDto.getSeries());
        ScorecardDto scorecardDto = new ScorecardDto();
        scorecardDto.setInning1Id(firstInningsSimulation.simulate(battingTeamId, bowlingTeamId, totalOvers, false, false, -1));
        int firstInningsScore = inningRepository.getInningScore(scorecardDto.getInning1Id());
        scorecardDto.setInning2Id(secondInningsSimulation.simulate(bowlingTeamId, battingTeamId, totalOvers, true, true, firstInningsScore));
        int secondInningsScore = inningRepository.getInningScore(scorecardDto.getInning2Id());
        if (firstInningsScore > secondInningsScore) {
            gameDto.setWinningTeamId(battingTeamId);
            teamRepository.increaseWins(battingTeamId);
            teamRepository.increaseLoss(bowlingTeamId);
            List<String> manOfMatch = this.calculateManOfMatch("Bowling", scorecardDto.getInning2Id());
            gameDto.setManOfMatch(manOfMatch.get(0));
            gameDto.setManOfMatchFigures(manOfMatch.get(1));
        } else {
            gameDto.setWinningTeamId(bowlingTeamId);
            teamRepository.increaseWins(battingTeamId);
            teamRepository.increaseLoss(bowlingTeamId);
            List<String> manOfMatch = this.calculateManOfMatch("Batting", scorecardDto.getInning2Id());
            gameDto.setManOfMatch(manOfMatch.get(0));
            gameDto.setManOfMatchFigures(manOfMatch.get(1));
        }
        int game_id = gameRepository.addGame(gameDto);
        if (gameSimulationDto.getSeries() != 1) {
            Series series = seriesRepository.getById(gameSimulationDto.getSeries());
            int winningTeamId = gameDto.getWinningTeamId();
            if (series.getTeam1Id() == winningTeamId) {
                seriesRepository.increaseTeam1Wins(gameSimulationDto.getSeries());
            } else {
                seriesRepository.increaseTeam2Wins(gameSimulationDto.getSeries());
            }
        }
        scorecardDto.setGameId(game_id);
        scorecardRepository.addScorecard(scorecardDto);
    }

    public List<String> calculateManOfMatch(String whichInning, int inningId) {
        List<String> manOfTheMatch = new ArrayList<String>();
        Inning deciderInnings = inningRepository.getById(inningId);
        if (whichInning.equals("Batting")) {
            manOfTheMatch.add(deciderInnings.getHighestScorer());
            manOfTheMatch.add(deciderInnings.getHighestScorerRuns() + " runs in " + deciderInnings.getHighestScorerBalls() + " balls");
        } else {
            manOfTheMatch.add(deciderInnings.getHighestWicketTaker());
            manOfTheMatch.add(deciderInnings.getHighestWicketTakerWickets() + "/" + deciderInnings.getHighestWicketTakerRunsGiven());
        }
        return manOfTheMatch;
    }

}
