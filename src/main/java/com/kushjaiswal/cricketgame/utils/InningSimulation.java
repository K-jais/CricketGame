package com.kushjaiswal.cricketgame.utils;

import com.kushjaiswal.cricketgame.dto.*;
import com.kushjaiswal.cricketgame.models.Player;
import com.kushjaiswal.cricketgame.repository.ball.BallRepository;
import com.kushjaiswal.cricketgame.repository.batsmanfigures.BatsmanFiguresRepository;
import com.kushjaiswal.cricketgame.repository.bowlerfigures.BowlerFiguresRepository;
import com.kushjaiswal.cricketgame.repository.fallOfWicket.FallOfWicketRepository;
import com.kushjaiswal.cricketgame.repository.inning.InningRepository;
import com.kushjaiswal.cricketgame.repository.over.OverRepository;
import com.kushjaiswal.cricketgame.repository.player.PlayerRepository;
import com.kushjaiswal.cricketgame.repository.team.TeamRepository;
import com.kushjaiswal.cricketgame.utils.battingbehaviour.BattingBehaviour;
import com.kushjaiswal.cricketgame.utils.battingbehaviour.types.MiddleOrderBatsman;
import com.kushjaiswal.cricketgame.utils.battingbehaviour.types.OpeningBatsman;
import com.kushjaiswal.cricketgame.utils.battingbehaviour.types.TailenderBatsman;
import com.kushjaiswal.cricketgame.utils.bowlingbehaviour.BowlingBehaviour;
import com.kushjaiswal.cricketgame.utils.bowlingbehaviour.types.MainBowler;
import com.kushjaiswal.cricketgame.utils.bowlingbehaviour.types.PartTimeBowler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class InningSimulation {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    InningRepository inningRepository;
    @Autowired
    OverRepository overRepository;
    @Autowired
    BallRepository ballRepository;
    @Autowired
    FallOfWicketRepository fallOfWicketRepository;
    @Autowired
    BatsmanFiguresRepository batsmanFiguresRepository;
    @Autowired
    BowlerFiguresRepository bowlerFiguresRepository;
    @Autowired
    OpeningBatsman openingBatsman;
    @Autowired
    MiddleOrderBatsman middleOrderBatsman;
    @Autowired
    TailenderBatsman tailenderBatsman;
    @Autowired
    MainBowler mainBowler;
    @Autowired
    PartTimeBowler partTimeBowler;

    public int simulate(int battingTeamId, int bowlingTeamId, int totalOvers, boolean toss, boolean firstInningHappened, int lastInningScore) {
        List<Player> battingFirst = playerRepository.getPlayersOfTeam(battingTeamId);
        List<Player> battingSecond = playerRepository.getPlayersOfTeam(bowlingTeamId);
        Player batsmanOnStrike = battingFirst.get(0);
        int batsmanOnStrikeIndex = 0;
        BatsmanFiguresDto batsmanOnStrikeFigures = new BatsmanFiguresDto();
        Player batsmanNonStrike = battingFirst.get(1);
        int batsmanNonStrikeIndex = 1;
        BatsmanFiguresDto batsmanNonStrikeFigures = new BatsmanFiguresDto();
        InningDto innings1 = new InningDto();
        innings1.setTotalOvers(totalOvers);
        innings1.setWinToss(toss);
        String teamName = teamRepository.getById(battingTeamId).getName();
        innings1.setTeamName(teamName);
        int inningsScore = 0;
        int totalBowlsBowled = 0;
        int totalWicketsGone = 0;
        int extras = 0;
        boolean inningsSimulationExit = false;
        List<OverDto> eachOverSimulation = new ArrayList();
        List<List<BallDto>> eachBallListSimulation = new ArrayList<List<BallDto>>();
        List<BowlerFiguresDto> bowlerFiguresDtoList = new ArrayList(Collections.nCopies(11, (Object) null));
        List<BatsmanFiguresDto> batsmanFiguresDtoList = new ArrayList(Collections.nCopies(11, (Object) null));
        List<FallOfWicketDto> fallOfWicketsSimulation = new ArrayList();
        List<String> highestWicketTaker = new ArrayList();
        List<String> highestRunScorer = new ArrayList();
        int overIterator = 1;
        int wicketsLeft = 10;

        int bowlerIterator;
        int ballIterator;
        while (overIterator <= totalOvers && wicketsLeft > 0 && !inningsSimulationExit) {
            for (bowlerIterator = 10; bowlerIterator > 5; --bowlerIterator) {
                Player bowler = (Player) battingSecond.get(bowlerIterator);
                BowlerFiguresDto bowlingFigures;
                if (bowlerFiguresDtoList.get(bowlerIterator) == null) {
                    bowlingFigures = new BowlerFiguresDto();
                } else {
                    bowlingFigures = bowlerFiguresDtoList.get(bowlerIterator);
                }

                List<BallDto> eachBallSimulation = new ArrayList();

                for (ballIterator = 0; ballIterator < 6; ++ballIterator) {
                    BallDto ball = new BallDto();
                    ball.setBatsmanOnStrike(batsmanOnStrike.getName());
                    BowlingBehaviour bowlingBehaviour = null;
                    if (bowler.getBowlingBehaviour() == Player.BowlingBehaviour.Main) {
                        bowlingBehaviour = mainBowler;
                    } else if (bowler.getBowlingBehaviour() == Player.BowlingBehaviour.PartTime) {
                        bowlingBehaviour = partTimeBowler;
                    }
                    BattingBehaviour battingBehaviour = null;
                    if (batsmanOnStrike.getBattingBehaviour() == Player.BattingBehaviour.Opening) {
                        battingBehaviour = openingBatsman;
                    } else if (batsmanOnStrike.getBattingBehaviour() == Player.BattingBehaviour.MiddleOrder) {
                        battingBehaviour = middleOrderBatsman;
                    } else {
                        battingBehaviour = tailenderBatsman;
                    }
                    bowlingBehaviour.setBattingBehaviour(battingBehaviour);
                    bowlingBehaviour.bowling();
                    int event = battingBehaviour.batting();
                    batsmanOnStrikeFigures.increaseBalls();
                    ++totalBowlsBowled;
                    if (event >= 0 && event <= 6) {
                        ball.setResult(event + " runs");
                        batsmanOnStrikeFigures.increaseRuns(event);
                        if (event == 1 || event == 3) {
                            Player tempBatsman = batsmanOnStrike;
                            batsmanOnStrike = batsmanNonStrike;
                            batsmanNonStrike = tempBatsman;
                            BatsmanFiguresDto tempBatsmanFigures = null;
                            tempBatsmanFigures = batsmanOnStrikeFigures;
                            batsmanOnStrikeFigures = batsmanNonStrikeFigures;
                            batsmanNonStrikeFigures = tempBatsmanFigures;
                            int tempSwapIndex = batsmanOnStrikeIndex;
                            batsmanOnStrikeIndex = batsmanNonStrikeIndex;
                            batsmanNonStrikeIndex = tempSwapIndex;
                        }

                        bowlingFigures.increaseRunsGiven(event);
                        inningsScore += event;
                    } else if (event == 7) {
                        ball.setResult("Wicket");
                        ++totalWicketsGone;
                        FallOfWicketDto fallOfWicketDto = new FallOfWicketDto();
                        fallOfWicketDto.setRuns(inningsScore);
                        fallOfWicketDto.setWicketNumber(batsmanOnStrike.getPosition());
                        fallOfWicketsSimulation.add(fallOfWicketDto);
                        --wicketsLeft;
                        bowlingFigures.increaseWickets();
                        batsmanOnStrikeFigures.setPosition(batsmanOnStrike.getPosition());
                        batsmanFiguresDtoList.set(batsmanOnStrikeIndex, batsmanOnStrikeFigures);
                        batsmanOnStrikeFigures = new BatsmanFiguresDto();
                        batsmanOnStrikeIndex = Math.max(batsmanNonStrikeIndex, batsmanOnStrikeIndex) + 1;
                        if (batsmanOnStrikeIndex <= 10) {
                            batsmanOnStrike = (Player) battingFirst.get(batsmanOnStrikeIndex);
                        }


                    } else if (event == 8) {
                        ball.setResult("Wide ball");
                        batsmanOnStrikeFigures.decreaseBalls();
                        ++inningsScore;
                        --ballIterator;
                        --totalBowlsBowled;
                        ++extras;
                    }

                    eachBallSimulation.add(ball);
                    if (firstInningHappened && inningsScore > lastInningScore && lastInningScore != -1) {
                        inningsSimulationExit = true;
                        break;
                    }
                    if (batsmanOnStrikeIndex > 10) {
                        break;
                    }
                }
                eachBallListSimulation.add(eachBallSimulation);
                OverDto over = new OverDto();
                over.setBowlerName(bowler.getName());
                eachOverSimulation.add(over);
                bowlingFigures.setPosition(bowler.getPosition());
                bowlerFiguresDtoList.set(bowlerIterator, bowlingFigures);
                if (wicketsLeft < 1) {
                    batsmanNonStrikeFigures.setNotout(true);
                    batsmanNonStrikeFigures.setPosition(batsmanNonStrike.getPosition());
                    batsmanFiguresDtoList.set(batsmanNonStrikeIndex, batsmanNonStrikeFigures);
                    break;
                }

                Player tempBatsman = null;
                tempBatsman = batsmanOnStrike;
                batsmanOnStrike = batsmanNonStrike;
                batsmanNonStrike = tempBatsman;
                BatsmanFiguresDto tempBatsmanFigures = batsmanOnStrikeFigures;
                batsmanOnStrikeFigures = batsmanNonStrikeFigures;
                batsmanNonStrikeFigures = tempBatsmanFigures;
                int tempSwapIndex = batsmanOnStrikeIndex;
                batsmanOnStrikeIndex = batsmanNonStrikeIndex;
                batsmanNonStrikeIndex = tempSwapIndex;
                ++overIterator;
                if (overIterator > totalOvers || inningsSimulationExit) {
                    batsmanOnStrikeFigures.setNotout(true);
                    batsmanOnStrikeFigures.setPosition(batsmanOnStrike.getPosition());
                    batsmanFiguresDtoList.set(batsmanOnStrikeIndex, batsmanOnStrikeFigures);
                    batsmanNonStrikeFigures.setPosition(batsmanNonStrike.getPosition());
                    batsmanNonStrikeFigures.setNotout(true);
                    batsmanFiguresDtoList.set(batsmanNonStrikeIndex, batsmanNonStrikeFigures);
                    break;
                }
            }
        }
        wicketsLeft = 0;
        bowlerIterator = 0;
        int runsMade = 0;
        int batsmanIterator = 0;

        for (int iterator = 0; iterator < batsmanFiguresDtoList.size(); ++iterator) {
            if (batsmanFiguresDtoList.get(iterator) != null && ((BatsmanFiguresDto) batsmanFiguresDtoList.get(iterator)).getRuns() > runsMade) {
                runsMade = ((BatsmanFiguresDto) batsmanFiguresDtoList.get(iterator)).getRuns();
                batsmanIterator = iterator;
            }
        }

        innings1.setHighestScorer(((Player) battingFirst.get(batsmanIterator)).getName());
        innings1.setHighestScorerRuns(runsMade);
        innings1.setHighestScorerBalls(batsmanFiguresDtoList.get(batsmanIterator).getBalls());
        int maximumWicketsTaken = ((BowlerFiguresDto) bowlerFiguresDtoList.get(10)).getWickets();
        int minimumRunsGiven = ((BowlerFiguresDto) bowlerFiguresDtoList.get(10)).getRunsGiven();
        int maximumWicketsTakenIndex = 10;

        for (int iterator = 9; iterator > 5; --iterator) {
            if (bowlerFiguresDtoList.get(iterator) != null && ((BowlerFiguresDto) bowlerFiguresDtoList.get(iterator)).getWickets() > maximumWicketsTaken) {
                maximumWicketsTaken = ((BowlerFiguresDto) bowlerFiguresDtoList.get(iterator)).getWickets();
                minimumRunsGiven = ((BowlerFiguresDto) bowlerFiguresDtoList.get(iterator)).getRunsGiven();
                maximumWicketsTakenIndex = iterator;
            } else if (bowlerFiguresDtoList.get(iterator) != null && ((BowlerFiguresDto) bowlerFiguresDtoList.get(iterator)).getWickets() == maximumWicketsTaken && ((BowlerFiguresDto) bowlerFiguresDtoList.get(iterator)).getRunsGiven() < minimumRunsGiven) {
                minimumRunsGiven = ((BowlerFiguresDto) bowlerFiguresDtoList.get(iterator)).getRunsGiven();
                maximumWicketsTakenIndex = iterator;
            }
        }
        innings1.setHighestWicketTaker(((Player) battingSecond.get(maximumWicketsTakenIndex)).getName());
        innings1.setHighestWicketTakerWickets(maximumWicketsTaken);
        innings1.setHighestWicketTakerRunsGiven(minimumRunsGiven);
        innings1.setTotalRunsScored(inningsScore);
        innings1.setTotalWicketsGone(totalWicketsGone);
        innings1.setExtras(extras);
        if (totalBowlsBowled % 6 == 0) {
            innings1.setTotalOversBowled(Double.parseDouble(Integer.toString(totalBowlsBowled / 6)));
        } else {
            String overBowled = Integer.toString(totalBowlsBowled / 6) + "." + Integer.toString(totalBowlsBowled % 6);
            innings1.setTotalOversBowled(Double.parseDouble(overBowled));
        }
        int inningId = inningRepository.addInning(innings1);
        for (int i = 0; i < eachOverSimulation.size(); i++) {
            eachOverSimulation.get(i).setInningId(inningId);
            int over_id = overRepository.addOver(eachOverSimulation.get(i));
            for (int j = 0; j < eachBallListSimulation.get(i).size(); j++) {
                eachBallListSimulation.get(i).get(j).setOverId(over_id);
                ballRepository.addBall(eachBallListSimulation.get(i).get(j));
            }
        }
        for (int i = 0; i < batsmanFiguresDtoList.size(); i++) {
            if (batsmanFiguresDtoList.get(i) != null) {
                batsmanFiguresDtoList.get(i).setInningId(inningId);
                batsmanFiguresRepository.addBatsmanFigures(batsmanFiguresDtoList.get(i));
                playerRepository.increaseRunsScored(battingTeamId, batsmanFiguresDtoList.get(i));
            }
        }
        for (int i = 0; i < bowlerFiguresDtoList.size(); i++) {
            if (bowlerFiguresDtoList.get(i) != null) {
                bowlerFiguresDtoList.get(i).setInningId(inningId);
                bowlerFiguresRepository.addBowlerFigures(bowlerFiguresDtoList.get(i));
                playerRepository.increaseWicketsTaken(bowlingTeamId, bowlerFiguresDtoList.get(i));
            }
        }
        for (int i = 0; i < fallOfWicketsSimulation.size(); i++) {
            fallOfWicketsSimulation.get(i).setInningId(inningId);
            fallOfWicketRepository.addFallOfWicket(fallOfWicketsSimulation.get(i));
        }
        return inningId;
    }
}
