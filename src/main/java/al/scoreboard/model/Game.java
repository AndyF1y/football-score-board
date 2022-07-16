package al.scoreboard.model;

import al.scoreboard.exception.ScoreBoardException;

import java.time.Instant;

public class Game {

    private final int id;

    private final String homeTeam;

    private final String awayTeam;

    private int homeTeamScore;

    private int awayTeamScore;

    private Instant startedAt;

    private Instant finishedAt;

    private Instant createdAt;

    public Game(int id, String homeTeam, String awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.createdAt = Instant.now();
    }

    public int getId() {
        return id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void start() {
        checkIsNotFinished();
        checkIsNotStarted();

        this.startedAt = Instant.now();
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
    }

    public void finish() {
        checkIsNotFinished();
        checkIsStarted();
        this.finishedAt = Instant.now();
    }

    public void updateScore(int homeTeamScore, int awayTeamScore) {
        checkIsNotFinished();
        checkIsStarted();

        if (homeTeamScore < 0 || awayTeamScore < 0)
            throw new ScoreBoardException("Score can't be less then 0");

        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public int getTotalScore() {
        return homeTeamScore + awayTeamScore;
    }

    private void checkIsNotFinished() {
        if (this.finishedAt != null)
            throw new ScoreBoardException("Game Already Finished");
    }

    private void checkIsNotStarted() {
        if (this.startedAt != null)
            throw new ScoreBoardException("Game Already Started");
    }

    private void checkIsStarted() {
        if (this.startedAt == null)
            throw new ScoreBoardException("Game Not Started");
    }
}
