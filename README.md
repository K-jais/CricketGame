# CricketGame

#Models

#Ball

ballId
batsmanOnStrike
result
overId ( references overId from Overball table)

#Overball

overId
bowlerName
inningId (references inningId from Inning table)

#Inning

inningId
totalOvers
isWinToss
teamName
totalRunsScored
totalOversBowled
totalWicketGone
extras
highestScorer
highestWicketTaker

#Scorecard

scorecardId
inning1Id (references inningId from Inning Table)
inning2Id (references inningId from Inning Table)
gameId (refernces gameId from Game Table)

#Game
gameId
team1Id (references teamId from Team table)
team2Id (references teamId from Team table)
winningTeamId (references teamId from Team table)
manOfMatch
seriesId (references seriesId from Series table)

#Series

seriesId
team1Id (references teamId from Team table)
team2Id (referenes teamId from Team table)
totalOvers
totalGames
sponsor
team1Wins
team2Wins

#BatsmanFigures

batsFigId
runs
balls
isNotOut
position
inningId (references inningId from Inning table)

#BolwerFigures

bowlFigId
wickets
runsGiven
position
inningId ( referenes inningId from Inning table)

#FallOfWicket

fallOfWicketId
runs
wicketNumber
inningId ( references inningId from Inning table)

#Team

teamId
name
matchesWin
matchesLose

#Player

playerId
name
age
runsScored
wicketsTaken
numberOfCenturies
fiveWicketHalls
battingBehaviour ( Opening, MiddleOrder, Tailender)
bowlingBehaviour (MainBowler, PartTime, NoBowler)
position
teamId ( references teamId from Team table)
