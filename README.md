# CricketGame

#Models

#Ball

ballId <br/>
batsmanOnStrike <br/>
result <br/>
overId ( references overId from Overball table) <br/>

#Overball

overId <br/>
bowlerName <br/>
inningId (references inningId from Inning table) <br/>

#Inning

inningId <br/>
totalOvers <br/>
isWinToss <br/>
teamName <br/>
totalRunsScored <br/>
totalOversBowled <br/>
totalWicketGone <br/>
extras <br/>
highestScorer <br/>
highestWicketTaker <br/>

#Scorecard

scorecardId <br/>
inning1Id (references inningId from Inning Table) <br/>
inning2Id (references inningId from Inning Table) <br/>
gameId (refernces gameId from Game Table) <br/>

#Game

gameId <br/>
team1Id (references teamId from Team table) <br/>
team2Id (references teamId from Team table) <br/>
winningTeamId (references teamId from Team table) <br/>
manOfMatch <br/>
seriesId (references seriesId from Series table) <br/>

#Series

seriesId <br/>
team1Id (references teamId from Team table) <br/>
team2Id (referenes teamId from Team table) <br/> <br/>
totalOvers <br/>
totalGames <br/>
sponsor <br/>
team1Wins <br/>
team2Wins <br/>

#BatsmanFigures

batsFigId <br/>
runs <br/>
balls <br/>
isNotOut <br/>
position <br/>
inningId (references inningId from Inning table) <br/>

#BolwerFigures

bowlFigId <br/>
wickets <br/>
runsGiven <br/>
position <br/>
inningId ( referenes inningId from Inning table) <br/>

#FallOfWicket

fallOfWicketId <br/>
runs <br/>
wicketNumber <br/>
inningId ( references inningId from Inning table) <br/>

#Team

teamId <br/>
name <br/>
matchesWin <br/>
matchesLose <br/>

#Player

playerId <br/>
name <br/>
age <br/>
runsScored <br/>
wicketsTaken <br/>
numberOfCenturies <br/>
fiveWicketHalls <br/>
battingBehaviour ( Opening, MiddleOrder, Tailender) <br/>
bowlingBehaviour (MainBowler, PartTime, NoBowler) <br/>
position <br/>
teamId ( references teamId from Team table) <br/>
