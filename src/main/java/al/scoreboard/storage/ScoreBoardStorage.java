package al.scoreboard.storage;

import al.scoreboard.exception.ScoreBoardException;
import al.scoreboard.model.Game;
import al.scoreboard.model.sequence.GameSequence;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardStorage {

    private static ScoreBoardStorage instance;

    public static ScoreBoardStorage newInstance() {
        instance = new ScoreBoardStorage();
        GameSequence.reset();
        return instance;
    }

    public static ScoreBoardStorage getInstance() {
        if (instance == null)
            instance = new ScoreBoardStorage();

        return instance;
    }

    private List<Game> games;

    private ScoreBoardStorage() {
        this.games = new ArrayList<>();
    }

    public Game newGame(String homeTeam, String awayTeam) {
        Game game = new Game(GameSequence.next(), homeTeam, awayTeam);
        games.add(game);
        return game;
    }

    public Game getGame(int id) {
        return games.stream().filter(game -> game.getId() == id).findFirst().orElseThrow(() -> new ScoreBoardException("Game not found"));
    }

    public void startGame(int id) {
        getGame(id).start();
    }

    public void updateScore(int id, int homeScore, int awayScore) {
        getGame(id).updateScore(homeScore, awayScore);
    }

    public void finishGame(int id) {
        getGame(id).finish();
    }

    public List<Game> listOrderedByTotalScore() {
        return games.stream().sorted(Comparator.comparingInt(Game::getTotalScore).reversed().thenComparing(Game::getCreatedAt)).collect(Collectors.toList());
    }
}
