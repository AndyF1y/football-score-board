package al.scoreboard;

import al.scoreboard.model.Game;
import al.scoreboard.storage.ScoreBoardStorage;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ScoreBoard {

    public static void reset() {
        ScoreBoardStorage.newInstance();
    }

    public static Game newGame(String homeTeam, String awayTeam) {
        return ScoreBoardStorage.getInstance().newGame(homeTeam, awayTeam);
    }

    public static Game getGame(int id) {
        return ScoreBoardStorage.getInstance().getGame(id);
    }

    public static void startGame(int id) {
        ScoreBoardStorage.getInstance().startGame(id);
    }

    public static void finishGame(int id) {
        ScoreBoardStorage.getInstance().finishGame(id);
    }

    public static void updateScore(int id, int homeScore, int awayScore) {
        ScoreBoardStorage.getInstance().updateScore(id, homeScore, awayScore);
    }

    public static List<Game> listOrderedByTotalScore() {
        return ScoreBoardStorage.getInstance().listOrderedByTotalScore();
    }

    public static void printOrderedByTotalScore() {
        AtomicInteger count = new AtomicInteger(0);
        System.out.printf("%s\n", listOrderedByTotalScore().stream().map(game -> String.format("%d. %s %s - %s %s", count.incrementAndGet(), game.getHomeTeam(), game.getHomeTeamScore(), game.getAwayTeam(), game.getAwayTeamScore())).collect(Collectors.joining("\n")));
    }

}
