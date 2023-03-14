package com.kushjaiswal.cricketgame.service.displaymodels.impl;

import com.kushjaiswal.cricketgame.displayModels.*;
import com.kushjaiswal.cricketgame.dto.BowlerOverDto;
import com.kushjaiswal.cricketgame.models.*;
import com.kushjaiswal.cricketgame.repository.ball.BallRepository;
import com.kushjaiswal.cricketgame.repository.fallOfWicket.FallOfWicketRepository;
import com.kushjaiswal.cricketgame.repository.game.GameRepository;
import com.kushjaiswal.cricketgame.repository.inning.InningRepository;
import com.kushjaiswal.cricketgame.repository.over.OverRepository;
import com.kushjaiswal.cricketgame.repository.player.PlayerRepository;
import com.kushjaiswal.cricketgame.repository.scorecard.ScorecardRepository;
import com.kushjaiswal.cricketgame.repository.series.SeriesRepository;
import com.kushjaiswal.cricketgame.repository.team.TeamRepository;
import com.kushjaiswal.cricketgame.service.displaymodels.DisplayModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class DisplayModelsServiceImpl implements DisplayModelsService {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    ScorecardRepository scorecardRepository;
    @Autowired
    InningRepository inningRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    OverRepository overRepository;
    @Autowired
    FallOfWicketRepository fallOfWicketRepository;
    @Autowired
    SeriesRepository seriesRepository;
    @Autowired
    BallRepository ballRepository;

    @Override
    public SmallScorecard getScorecardMatch(int id) {
        SmallScorecard smallScorecard = new SmallScorecard();
        Game game = gameRepository.getById(id);
        smallScorecard.setManOfMatchName(game.getManOfMatch());
        smallScorecard.setManOfMatchFigures(game.getManOfMatchFigures());
        Scorecard scorecard = scorecardRepository.getByGameId(id);
        int inning1_id = scorecard.getInning1Id();
        Inning inning1 = inningRepository.getById(inning1_id);
        int inning2_id = scorecard.getInning2Id();
        Inning inning2 = inningRepository.getById(inning2_id);
        Team team1 = teamRepository.getTeamByName(inning1.getTeamName());
        Team team2 = teamRepository.getTeamByName(inning2.getTeamName());
        int team1_id = team1.getTeamId();
        int team2_id = team2.getTeamId();
        List<Player> team1_players = playerRepository.getPlayersOfTeam(team1_id);
        List<Player> team2_players = playerRepository.getPlayersOfTeam(team2_id);
        List<BatsmanFigures> inning1_batsmans = inningRepository.getBatsmanFigures(inning1_id);
        List<BowlerFigures> inning1_bowlers = inningRepository.getBowlerFigures(inning1_id);
        List<BatsmanFigures> inning2_batsmans = inningRepository.getBatsmanFigures(inning2_id);
        List<BowlerFigures> inning2_bowlers = inningRepository.getBowlerFigures(inning2_id);
        OneTeamScorecard oneTeamScorecard = new OneTeamScorecard();
        getOneTeamScorecard(oneTeamScorecard, team1_players, team2_players, inning1_batsmans, inning1_bowlers, inning1);
        smallScorecard.setFirstInningScorecard(oneTeamScorecard);
        oneTeamScorecard = new OneTeamScorecard();
        getOneTeamScorecard(oneTeamScorecard, team2_players, team1_players, inning2_batsmans, inning2_bowlers, inning2);
        smallScorecard.setSecondInningScorecard(oneTeamScorecard);
        String result = " ";
        if (inning1.getTotalRunsScored() > inning2.getTotalRunsScored()) {
            result += inning1.getTeamName() + " won by " + String.valueOf(inning1.getTotalRunsScored() - inning2.getTotalRunsScored()) + " runs";
        } else {
            result += inning2.getTeamName() + " won by " + String.valueOf(10 - inning2.getTotalWicketsGone()) + " wickets";
        }
        smallScorecard.setMatchResult(result);
        return smallScorecard;
    }

    @Override
    public SeriesScorecard getScorecardSeries(int id) {
        Series series = seriesRepository.getById(id);
        int series_id = series.getSeriesId();
        int team1_id = series.getTeam1Id();
        int team2_id = series.getTeam2Id();
        Team team1 = teamRepository.getById(team1_id);
        Team team2 = teamRepository.getById(team2_id);
        SeriesScorecard seriesScorecard = new SeriesScorecard();
        seriesScorecard.setTeam1(team1.getName());
        seriesScorecard.setTeam2(team2.getName());
        seriesScorecard.setSponsor(series.getSponsor());
        seriesScorecard.setTotalGames(series.getTotalGames());
        seriesScorecard.setTotalOvers(series.getTotalOvers());
        String result = team1.getName() + " (" + series.getTeam1Wins() + " - " + series.getTeam2Wins() + ") " + team2.getName();
        seriesScorecard.setResult(result);
        List<Game> seriesGames = gameRepository.getSeriesMatches(series_id);
        List<SmallScorecard> smallScorecardList = new ArrayList<SmallScorecard>();
        for (int iterator = 0; iterator < seriesGames.size(); iterator++) {
            smallScorecardList.add(getScorecardMatch(seriesGames.get(iterator).getGameId()));
        }
        seriesScorecard.setSmallScorecardList(smallScorecardList);
        return seriesScorecard;
    }

    @Override
    public HighestScorerSeries getHighestScorer(int id) {
        Series series = seriesRepository.getById(id);
        int series_id = series.getSeriesId();
        int team1_id = series.getTeam1Id();
        int team2_id = series.getTeam2Id();
        Team team1 = teamRepository.getById(team1_id);
        Team team2 = teamRepository.getById(team2_id);
        List<Player> team1_players = playerRepository.getPlayersOfTeam(team1_id);
        List<Player> team2_players = playerRepository.getPlayersOfTeam(team2_id);
        List<Game> seriesGames = gameRepository.getSeriesMatches(series_id);
        List<SmallScorecard> smallScorecardList = new ArrayList<SmallScorecard>();
        for (int iterator = 0; iterator < seriesGames.size(); iterator++) {
            smallScorecardList.add(getScorecardMatch(seriesGames.get(iterator).getGameId()));
        }
        List<Integer> allPlayerScoresTeam1 = new ArrayList(Collections.nCopies(11, 0));
        List<Integer> allPlayerScoresTeam2 = new ArrayList(Collections.nCopies(11, 0));
        for (int iterator = 0; iterator < smallScorecardList.size(); iterator++) {
            OneTeamScorecard oneTeamScorecardFirst = smallScorecardList.get(iterator).getFirstInningScorecard();
            List<BatsmanFigureDisplay> batsmanFigureDisplayList = oneTeamScorecardFirst.getBatsmanFiguresDiplayList();
            if (oneTeamScorecardFirst.getTeamName().equals(team1.getName())) {
                for (int iterator1 = 0; iterator1 < batsmanFigureDisplayList.size(); iterator1++) {
                    allPlayerScoresTeam1.set(iterator1, allPlayerScoresTeam1.get(iterator1) + batsmanFigureDisplayList.get(iterator1).getRuns());
                }
            } else {
                for (int iterator1 = 0; iterator1 < batsmanFigureDisplayList.size(); iterator1++) {
                    allPlayerScoresTeam2.set(iterator1, allPlayerScoresTeam2.get(iterator1) + batsmanFigureDisplayList.get(iterator1).getRuns());
                }
            }


            OneTeamScorecard oneTeamScorecardSecond = smallScorecardList.get(iterator).getSecondInningScorecard();
            List<BatsmanFigureDisplay> batsmanFigureDisplayList1 = oneTeamScorecardSecond.getBatsmanFiguresDiplayList();
            if (oneTeamScorecardSecond.getTeamName().equals(team1.getName())) {
                for (int iterator1 = 0; iterator1 < batsmanFigureDisplayList1.size(); iterator1++) {
                    allPlayerScoresTeam1.set(iterator1, allPlayerScoresTeam1.get(iterator1) + batsmanFigureDisplayList1.get(iterator1).getRuns());
                }
            } else {
                for (int iterator1 = 0; iterator1 < batsmanFigureDisplayList1.size(); iterator1++) {
                    allPlayerScoresTeam2.set(iterator1, allPlayerScoresTeam2.get(iterator1) + batsmanFigureDisplayList1.get(iterator1).getRuns());
                }
            }
        }
        List<Integer> highestScorerTeam1 = getMaximumScoreIndex(allPlayerScoresTeam1);
        List<Integer> highestScorerTeam2 = getMaximumScoreIndex(allPlayerScoresTeam2);
        HighestScorerSeries highestScorerSeries = new HighestScorerSeries();
        if (highestScorerTeam1.get(1) > highestScorerTeam2.get(1)) {
            highestScorerSeries.setRuns(highestScorerTeam1.get(1));
            highestScorerSeries.setPosition(highestScorerTeam1.get(0) + 1);
            highestScorerSeries.setName(team1_players.get(highestScorerTeam1.get(0)).getName());
        } else {
            highestScorerSeries.setRuns(highestScorerTeam2.get(1));
            highestScorerSeries.setPosition(highestScorerTeam2.get(0) + 1);
            highestScorerSeries.setName(team2_players.get(highestScorerTeam2.get(0)).getName());
        }
        double average = (double) highestScorerSeries.getRuns() / (double) series.getTotalGames();
        average = Math.round(average * 100) / 100.0;
        highestScorerSeries.setAverage(average);
        return highestScorerSeries;
    }

    @Override
    public HighestWicketTakerSeries getHighestWicketTaker(int id) {
        Series series = seriesRepository.getById(id);
        int series_id = series.getSeriesId();
        int team1_id = series.getTeam1Id();
        int team2_id = series.getTeam2Id();
        Team team1 = teamRepository.getById(team1_id);
        Team team2 = teamRepository.getById(team2_id);
        List<Player> team1_players = playerRepository.getPlayersOfTeam(team1_id);
        List<Player> team2_players = playerRepository.getPlayersOfTeam(team2_id);
        List<Game> seriesGames = gameRepository.getSeriesMatches(series_id);
        List<SmallScorecard> smallScorecardList = new ArrayList<SmallScorecard>();
        for (int iterator = 0; iterator < seriesGames.size(); iterator++) {
            smallScorecardList.add(getScorecardMatch(seriesGames.get(iterator).getGameId()));
        }
        List<Integer> allPlayerWicketsTeam1 = new ArrayList(Collections.nCopies(11, 0));
        List<Double> allPlayerEconomyTeam1 = new ArrayList(Collections.nCopies(11, 0.0));
        List<Integer> allPlayerWicketsTeam2 = new ArrayList(Collections.nCopies(11, 0));
        List<Double> allPlayerEconomyTeam2 = new ArrayList(Collections.nCopies(11, 0.0));
        for (int iterator = 0; iterator < smallScorecardList.size(); iterator++) {
            OneTeamScorecard oneTeamScorecardFirst = smallScorecardList.get(iterator).getFirstInningScorecard();
            List<BowlerFigureDisplay> bowlerFigureDisplayList = oneTeamScorecardFirst.getBowlerFiguresDiplayList();
            if (!oneTeamScorecardFirst.getTeamName().equals(team1.getName())) {
                for (int iterator1 = 0; iterator1 < bowlerFigureDisplayList.size(); iterator1++) {
                    allPlayerWicketsTeam1.set(iterator1 + 6, allPlayerWicketsTeam1.get(iterator1 + 6) + bowlerFigureDisplayList.get(iterator1).getWickets());
                    allPlayerEconomyTeam1.set(iterator1 + 6, allPlayerEconomyTeam1.get(iterator1 + 6) + bowlerFigureDisplayList.get(iterator1).getEconomy());
                }
            } else {
                for (int iterator1 = 0; iterator1 < bowlerFigureDisplayList.size(); iterator1++) {
                    allPlayerWicketsTeam2.set(iterator1 + 6, allPlayerWicketsTeam2.get(iterator1 + 6) + bowlerFigureDisplayList.get(iterator1).getWickets());
                    allPlayerEconomyTeam2.set(iterator1 + 6, allPlayerEconomyTeam2.get(iterator1 + 6) + bowlerFigureDisplayList.get(iterator1).getEconomy());
                }
            }
            OneTeamScorecard oneTeamScorecardSecond = smallScorecardList.get(iterator).getSecondInningScorecard();
            List<BowlerFigureDisplay> bowlerFigureDisplayList1 = oneTeamScorecardSecond.getBowlerFiguresDiplayList();
            if (!oneTeamScorecardSecond.getTeamName().equals(team1.getName())) {
                for (int iterator1 = 0; iterator1 < bowlerFigureDisplayList1.size(); iterator1++) {
                    allPlayerWicketsTeam1.set(iterator1 + 6, allPlayerWicketsTeam1.get(iterator1 + 6) + bowlerFigureDisplayList1.get(iterator1).getWickets());
                    allPlayerEconomyTeam1.set(iterator1 + 6, allPlayerEconomyTeam1.get(iterator1 + 6) + bowlerFigureDisplayList1.get(iterator1).getEconomy());
                }
            } else {
                for (int iterator1 = 0; iterator1 < bowlerFigureDisplayList1.size(); iterator1++) {
                    allPlayerWicketsTeam2.set(iterator1 + 6, allPlayerWicketsTeam2.get(iterator1 + 6) + bowlerFigureDisplayList1.get(iterator1).getWickets());
                    allPlayerEconomyTeam2.set(iterator1 + 6, allPlayerEconomyTeam2.get(iterator1 + 6) + bowlerFigureDisplayList1.get(iterator1).getEconomy());
                }
            }
        }
        List<Integer> highestWicketTeam1 = getMaximumScoreIndex(allPlayerWicketsTeam1);
        List<Integer> highestWicketTeam2 = getMaximumScoreIndex(allPlayerWicketsTeam2);
        HighestWicketTakerSeries highestWicketTakerSeries = new HighestWicketTakerSeries();
        if (highestWicketTeam1.get(1) > highestWicketTeam2.get(1)) {
            highestWicketTakerSeries.setWickets(highestWicketTeam1.get(1));
            highestWicketTakerSeries.setPosition(highestWicketTeam1.get(0) + 1);
            highestWicketTakerSeries.setName(team1_players.get(highestWicketTeam1.get(0)).getName());
            double economy = (double) allPlayerEconomyTeam1.get(highestWicketTeam1.get(0)) / (double) series.getTotalGames();
            economy = Math.round(economy * 100) / 100.0;
            highestWicketTakerSeries.setEconomy(economy);
        } else {
            highestWicketTakerSeries.setWickets(highestWicketTeam2.get(1));
            highestWicketTakerSeries.setPosition(highestWicketTeam2.get(0) + 1);
            highestWicketTakerSeries.setName(team2_players.get(highestWicketTeam2.get(0)).getName());
            double economy = (double) allPlayerEconomyTeam2.get(highestWicketTeam2.get(0)) / (double) series.getTotalGames();
            economy = Math.round(economy * 100) / 100.0;
            highestWicketTakerSeries.setEconomy(economy);
        }

        return highestWicketTakerSeries;
    }

    @Override
    public PlayerRecord getPlayerRecord(int id, int id1) {
        Series series = seriesRepository.getById(id);
        Player player = playerRepository.getById(id1);
        int team_id = player.getTeamId();
        Team team = teamRepository.getById(team_id);
        List<Game> seriesGames = gameRepository.getSeriesMatches(id);
        List<SmallScorecard> smallScorecardList = new ArrayList<SmallScorecard>();
        for (int iterator = 0; iterator < seriesGames.size(); iterator++) {
            smallScorecardList.add(getScorecardMatch(seriesGames.get(iterator).getGameId()));
        }
        int runs = 0, wickets = 0;
        double strikeRate = 0.0, economy = 0.0;
        for (int iterator = 0; iterator < smallScorecardList.size(); iterator++) {
            OneTeamScorecard oneTeamScorecardFirst = new OneTeamScorecard();
            if (smallScorecardList.get(iterator).getFirstInningScorecard().getTeamName().equals(team.getName())) {
                if (smallScorecardList.get(iterator).getFirstInningScorecard().getBatsmanFiguresDiplayList().size() >= player.getPosition()) {
                    runs += smallScorecardList.get(iterator).getFirstInningScorecard().getBatsmanFiguresDiplayList().get(player.getPosition() - 1).getRuns();
                    strikeRate += smallScorecardList.get(iterator).getFirstInningScorecard().getBatsmanFiguresDiplayList().get(player.getPosition() - 1).getStrikeRate();
                }
                if (player.getPosition() >= 7 && player.getPosition() <= 11) {
                    wickets += smallScorecardList.get(iterator).getSecondInningScorecard().getBowlerFiguresDiplayList().get(player.getPosition() - 7).getWickets();
                    economy += smallScorecardList.get(iterator).getSecondInningScorecard().getBowlerFiguresDiplayList().get(player.getPosition() - 7).getEconomy();
                }
            } else {
                if (smallScorecardList.get(iterator).getSecondInningScorecard().getBatsmanFiguresDiplayList().size() >= player.getPosition()) {
                    runs += smallScorecardList.get(iterator).getSecondInningScorecard().getBatsmanFiguresDiplayList().get(player.getPosition() - 1).getRuns();
                    strikeRate += smallScorecardList.get(iterator).getSecondInningScorecard().getBatsmanFiguresDiplayList().get(player.getPosition() - 1).getStrikeRate();
                }
                if (player.getPosition() >= 7 && player.getPosition() <= 11) {
                    wickets += smallScorecardList.get(iterator).getFirstInningScorecard().getBowlerFiguresDiplayList().get(player.getPosition() - 7).getWickets();
                    economy += smallScorecardList.get(iterator).getFirstInningScorecard().getBowlerFiguresDiplayList().get(player.getPosition() - 7).getEconomy();
                }
            }
        }
        int totalGames = series.getTotalGames();
        strikeRate = (double) strikeRate / (double) totalGames;
        strikeRate = Math.round(strikeRate * 100) / 100.0;
        economy = (double) economy / (double) totalGames;
        economy = Math.round(economy * 100) / 100.0;
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.setName(player.getName());
        playerRecord.setPosition(player.getPosition());
        playerRecord.setTeamName(team.getName());
        playerRecord.setRuns(runs);
        playerRecord.setEconomy(economy);
        double average = (double) runs / (double) totalGames;
        average = Math.round(average * 100) / 100.0;
        playerRecord.setAverage(average);
        playerRecord.setStrikeRate(strikeRate);
        playerRecord.setWickets(wickets);
        return playerRecord;
    }

    @Override
    public OverScorecard getOverScorecard(int id) {
        OverScorecard overScorecard = new OverScorecard();
        Scorecard scorecard = scorecardRepository.getByGameId(id);
        int inning1_id = scorecard.getInning1Id();
        Inning inning1 = inningRepository.getById(inning1_id);
        int inning2_id = scorecard.getInning2Id();
        Inning inning2 = inningRepository.getById(inning2_id);
        overScorecard.setFirstInningBowlingTeamName(inning2.getTeamName());
        overScorecard.setSecondInningBowlingTeamName(inning1.getTeamName());
        overScorecard.setFirstInning(getOverInning(inning1));
        overScorecard.setSecondInning(getOverInning(inning2));
        return overScorecard;
    }

    @Override
    public OverBowler getOverBowler(int id, int id1) {
        OverBowler overBowler = new OverBowler();
        Scorecard scorecard = scorecardRepository.getByGameId(id);
        int inning1_id = scorecard.getInning1Id();
        Inning inning1 = inningRepository.getById(inning1_id);
        int inning2_id = scorecard.getInning2Id();
        Inning inning2 = inningRepository.getById(inning2_id);
        Player player = playerRepository.getById(id1);
        Team team = teamRepository.getById(player.getTeamId());
        overBowler.setName(player.getName());
        overBowler.setPosition(player.getPosition());
        overBowler.setTeamName(team.getName());
        if (!inning1.getTeamName().equals(team.getName())) {
            overBowler.setOverInningList(getOverInningBowler(inning1, player.getName()));
        } else {
            overBowler.setOverInningList(getOverInningBowler(inning2, player.getName()));
        }
        return overBowler;
    }

    @Override
    public PlayerRecordAgainst getPlayerRecordAgainst(int id, int id1) {
        PlayerRecordAgainst playerRecordAgainst = new PlayerRecordAgainst();
        Player player = playerRepository.getById(id);
        Team team = teamRepository.getById(player.getTeamId());
        Team teamAgainst = teamRepository.getById(id1);
        playerRecordAgainst.setPosition(player.getPosition());
        playerRecordAgainst.setName(player.getName());
        playerRecordAgainst.setTeamName(team.getName());
        playerRecordAgainst.setTeamAgainst(teamAgainst.getName());
        List<Game> gamesList = gameRepository.getMatchesBetween(team.getTeamId(), teamAgainst.getTeamId());
        List<SmallScorecard> smallScorecardList = new ArrayList<SmallScorecard>();
        for (int iterator = 0; iterator < gamesList.size(); iterator++) {
            smallScorecardList.add(getScorecardMatch(gamesList.get(iterator).getGameId()));
        }
        int runs = 0, wickets = 0;
        double strikeRate = 0.0, economy = 0.0;
        for (int iterator = 0; iterator < smallScorecardList.size(); iterator++) {
            OneTeamScorecard oneTeamScorecardFirst = new OneTeamScorecard();
            if (smallScorecardList.get(iterator).getFirstInningScorecard().getTeamName().equals(team.getName())) {
                if (smallScorecardList.get(iterator).getFirstInningScorecard().getBatsmanFiguresDiplayList().size() >= player.getPosition()) {
                    runs += smallScorecardList.get(iterator).getFirstInningScorecard().getBatsmanFiguresDiplayList().get(player.getPosition() - 1).getRuns();
                    strikeRate += smallScorecardList.get(iterator).getFirstInningScorecard().getBatsmanFiguresDiplayList().get(player.getPosition() - 1).getStrikeRate();
                }
                if (player.getPosition() >= 7 && player.getPosition() <= 11) {
                    wickets += smallScorecardList.get(iterator).getSecondInningScorecard().getBowlerFiguresDiplayList().get(player.getPosition() - 7).getWickets();
                    economy += smallScorecardList.get(iterator).getSecondInningScorecard().getBowlerFiguresDiplayList().get(player.getPosition() - 7).getEconomy();
                }
            } else {
                if (smallScorecardList.get(iterator).getSecondInningScorecard().getBatsmanFiguresDiplayList().size() >= player.getPosition()) {
                    runs += smallScorecardList.get(iterator).getSecondInningScorecard().getBatsmanFiguresDiplayList().get(player.getPosition() - 1).getRuns();
                    strikeRate += smallScorecardList.get(iterator).getSecondInningScorecard().getBatsmanFiguresDiplayList().get(player.getPosition() - 1).getStrikeRate();
                }
                if (player.getPosition() >= 7 && player.getPosition() <= 11) {
                    wickets += smallScorecardList.get(iterator).getFirstInningScorecard().getBowlerFiguresDiplayList().get(player.getPosition() - 7).getWickets();
                    economy += smallScorecardList.get(iterator).getFirstInningScorecard().getBowlerFiguresDiplayList().get(player.getPosition() - 7).getEconomy();
                }
            }
        }
        int totalGames = gamesList.size();
        strikeRate = (double) strikeRate / (double) totalGames;
        strikeRate = Math.round(strikeRate * 100) / 100.0;
        economy = (double) economy / (double) totalGames;
        economy = Math.round(economy * 100) / 100.0;
        double average = (double) runs / (double) totalGames;
        average = Math.round(average * 100) / 100.0;
        playerRecordAgainst.setRuns(runs);
        playerRecordAgainst.setWickets(wickets);
        playerRecordAgainst.setAverage(average);
        playerRecordAgainst.setEconomy(economy);
        playerRecordAgainst.setStrikeRate(strikeRate);
        return playerRecordAgainst;
    }

    private List<OverInning> getOverInningBowler(Inning inning, String name) {
        List<OverInning> overInningList = new ArrayList<OverInning>();
        int inning_id = inning.getInningId();
        BowlerOverDto bowlerOverDto = new BowlerOverDto(name, inning_id);
        List<Overball> overs = overRepository.getBowlerOvers(bowlerOverDto);
        for (int iterator = 0; iterator < overs.size(); iterator++) {
            OverInning overInning = new OverInning();
            overInning.setOverNumber(iterator + 1);
            overInning.setBowlerName(overs.get(iterator).getBowlerName());
            List<BallDisplay> ballDisplayList = new ArrayList<BallDisplay>();
            List<Ball> ballList = ballRepository.getOverBalls(overs.get(iterator).getOverId());
            for (int iterator1 = 0; iterator1 < ballList.size(); iterator1++) {
                BallDisplay ballDisplay = new BallDisplay();
                ballDisplay.setBatsmanOnStrike(ballList.get(iterator1).getBatsmanOnStrike());
                ballDisplay.setResult(ballList.get(iterator1).getResult());
                ballDisplayList.add(ballDisplay);
            }
            overInning.setBallDisplayList(ballDisplayList);
            overInningList.add(overInning);
        }
        return overInningList;
    }

    private List<OverInning> getOverInning(Inning inning) {
        List<OverInning> overInningList = new ArrayList<OverInning>();
        int inning_id = inning.getInningId();
        List<Overball> overs = overRepository.getOversInning(inning_id);
        for (int iterator = 0; iterator < overs.size(); iterator++) {
            OverInning overInning = new OverInning();
            overInning.setOverNumber(iterator + 1);
            overInning.setBowlerName(overs.get(iterator).getBowlerName());
            List<BallDisplay> ballDisplayList = new ArrayList<BallDisplay>();
            List<Ball> ballList = ballRepository.getOverBalls(overs.get(iterator).getOverId());
            for (int iterator1 = 0; iterator1 < ballList.size(); iterator1++) {
                BallDisplay ballDisplay = new BallDisplay();
                ballDisplay.setBatsmanOnStrike(ballList.get(iterator1).getBatsmanOnStrike());
                ballDisplay.setResult(ballList.get(iterator1).getResult());
                ballDisplayList.add(ballDisplay);
            }
            overInning.setBallDisplayList(ballDisplayList);
            overInningList.add(overInning);
        }
        return overInningList;
    }

    private List<Integer> getMaximumScoreIndex(List<Integer> allPlayerScoresTeam) {
        List<Integer> highestScorer = new ArrayList<Integer>();
        int highestIndex = 0, highestRuns = allPlayerScoresTeam.get(0);
        for (int iterator = 1; iterator < allPlayerScoresTeam.size(); iterator++) {
            if (highestRuns < allPlayerScoresTeam.get(iterator)) {
                highestRuns = allPlayerScoresTeam.get(iterator);
                highestIndex = iterator;
            }
        }
        highestScorer.add(highestIndex);
        highestScorer.add(highestRuns);
        return highestScorer;
    }

    private void getOneTeamScorecard(OneTeamScorecard oneTeamScorecard, List<Player> team1_players, List<Player> team2_players, List<BatsmanFigures> inning_batsmans, List<BowlerFigures> inning_bowlers, Inning inning) {
        oneTeamScorecard.setTeamName(inning.getTeamName());
        oneTeamScorecard.setTeamTotal(inning.getTotalRunsScored());
        oneTeamScorecard.setTotalWicketsGone(inning.getTotalWicketsGone());
        oneTeamScorecard.setTotalOversBowled(inning.getTotalOversBowled());
        oneTeamScorecard.setExtras(inning.getExtras());
        oneTeamScorecard.setTossWin(inning.isWinToss());
        double oversBowled = Double.valueOf(inning.getTotalOversBowled());
        int oversBowled_int = (int) (oversBowled);
        double runRate = (double) inning.getTotalRunsScored() / (double) oversBowled_int;
        runRate = Math.round(runRate * 100) / 100.0;
        oneTeamScorecard.setRunRate(runRate);
        List<BatsmanFigureDisplay> batsmanFigureDisplayList = new ArrayList<BatsmanFigureDisplay>();
        List<BowlerFigureDisplay> bowlerFigureDisplayList = new ArrayList<BowlerFigureDisplay>();
        for (int iterator = 0; iterator < inning_batsmans.size(); iterator++) {
            BatsmanFigureDisplay batsmanFigureDisplay = new BatsmanFigureDisplay();
            Player player = team1_players.get(iterator);
            BatsmanFigures batsmanFigures = inning_batsmans.get(iterator);
            batsmanFigureDisplay.setRuns(batsmanFigures.getRuns());
            batsmanFigureDisplay.setBalls(batsmanFigures.getBalls());
            batsmanFigureDisplay.setNotOut(batsmanFigures.isNotout());
            batsmanFigureDisplay.setPosition(batsmanFigures.getPosition());
            batsmanFigureDisplay.setPlayerName(player.getName());
            double strikeRate = (double) batsmanFigures.getRuns() / (double) batsmanFigures.getBalls();
            strikeRate = Math.round(strikeRate * 100) / 100.0;
            batsmanFigureDisplay.setStrikeRate(100 * strikeRate);
            batsmanFigureDisplayList.add(batsmanFigureDisplay);
        }
        oneTeamScorecard.setBatsmanFiguresDiplayList(batsmanFigureDisplayList);
        for (int iterator = 0; iterator < inning_bowlers.size(); iterator++) {
            BowlerFigureDisplay bowlerFigureDisplay = new BowlerFigureDisplay();
            Player player = team2_players.get(iterator + 6);
            BowlerFigures bowlerFigures = inning_bowlers.get(iterator);
            bowlerFigureDisplay.setPosition(bowlerFigures.getPosition());
            bowlerFigureDisplay.setPlayerName(player.getName());
            BowlerOverDto bowlerOverDto = new BowlerOverDto(player.getName(), inning.getInningId());
            int totalOvers = overRepository.getBowlerOvers(bowlerOverDto).size();
            bowlerFigureDisplay.setTotalOvers(totalOvers);
            bowlerFigureDisplay.setRunsGiven(bowlerFigures.getRunsGiven());
            bowlerFigureDisplay.setWickets(bowlerFigures.getWickets());
            double economy = (double) bowlerFigures.getRunsGiven() / (double) totalOvers;
            economy = Math.round(economy * 100) / 100.0;
            bowlerFigureDisplay.setEconomy(economy);
            bowlerFigureDisplayList.add(bowlerFigureDisplay);
        }
        oneTeamScorecard.setBowlerFiguresDiplayList(bowlerFigureDisplayList);
        List<FallOfWicketDisplay> fallOfWicketDisplayList = new ArrayList<FallOfWicketDisplay>();
        List<FallOfWicket> fallOfWicketList = fallOfWicketRepository.getFallOfWicketsInInnings(inning.getInningId());
        for (int iterator = 0; iterator < fallOfWicketList.size(); iterator++) {
            FallOfWicketDisplay fallOfWicketDisplay = new FallOfWicketDisplay();
            Player player = team1_players.get(iterator);
            FallOfWicket fallOfWicket = fallOfWicketList.get(iterator);
            fallOfWicketDisplay.setWicketNumber(iterator + 1);
            fallOfWicketDisplay.setPlayerName(player.getName());
            fallOfWicketDisplay.setRunsAt(fallOfWicket.getRuns());
            fallOfWicketDisplayList.add(fallOfWicketDisplay);
        }
        Collections.sort(fallOfWicketDisplayList, new Comparator<FallOfWicketDisplay>() {
            @Override
            public int compare(FallOfWicketDisplay f1, FallOfWicketDisplay f2) {
                return f1.getRunsAt() - f2.getRunsAt();
            }
        });
        for (int iterator = 0; iterator < fallOfWicketDisplayList.size(); iterator++) {
            fallOfWicketDisplayList.get(iterator).setWicketNumber(iterator + 1);
        }
        oneTeamScorecard.setFallOfWicketDisplayList(fallOfWicketDisplayList);
    }
}
