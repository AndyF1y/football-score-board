package al.scoreboard;

import al.scoreboard.exception.ScoreBoardException;
import al.scoreboard.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {

    @BeforeEach
    public void before() {
        ScoreBoard.reset();
    }

    @Test
    public void gameShouldAddStartAndFinish() {
        int id = ScoreBoard.newGame("England", "Brazil").getId();
        ScoreBoard.startGame(id);
        verifyGameStarted(id);
        ScoreBoard.updateScore(id, 0, 1);
        verifyGameScore(id, 0, 1);
        ScoreBoard.updateScore(id, 1, 1);
        verifyGameScore(id, 1, 1);
        ScoreBoard.updateScore(id, 3, 1);
        verifyGameScore(id, 3, 1);
        ScoreBoard.finishGame(id);
        verifyGameFinished(id);
    }

    @Test
    public void gamesListedInOrderByTotalScore() {
        Random random = new Random();
        for (int i = 0; i < 20; i += 2) {
            int teamHome = i;
            int teamAway = i + 1;
            addGame("team" + teamHome, "team" + teamAway, random.nextInt(15), random.nextInt(15));
        }

        List<Game> games = ScoreBoard.listOrderedByTotalScore();
        ScoreBoard.printOrderedByTotalScore();
        for (int i = 0; i < games.size() - 1; i++) {
            Game first = games.get(i);
            Game second = games.get(i + 1);
            assertTrue(first.getTotalScore() >= second.getTotalScore() || !first.getCreatedAt().isBefore(second.getCreatedAt()));
        }
    }

    @Test
    public void shouldFailedForStartStartedGame() {
        int id = ScoreBoard.newGame("England", "Italy").getId();
        ScoreBoard.startGame(id);
        assertThrows(ScoreBoardException.class, () -> ScoreBoard.startGame(id));
    }

    @Test
    public void shouldFailedForFinishFinishedGame() {
        int id = ScoreBoard.newGame("England", "Italy").getId();
        ScoreBoard.startGame(id);
        ScoreBoard.finishGame(id);
        assertThrows(ScoreBoardException.class, () -> ScoreBoard.finishGame(id));
    }

    @Test
    public void shouldFailedForUpdateScoreForFinishedGame() {
        int id = ScoreBoard.newGame("England", "Italy").getId();
        ScoreBoard.startGame(id);
        ScoreBoard.finishGame(id);
        assertThrows(ScoreBoardException.class, () -> ScoreBoard.updateScore(id, 1, 2));
    }

    @Test
    public void shouldFailedForUpdateScoreWithNegativeValue() {
        int id = ScoreBoard.newGame("England", "Italy").getId();
        ScoreBoard.startGame(id);
        assertThrows(ScoreBoardException.class, () -> ScoreBoard.updateScore(id, 1, -2));
    }

    @Test
    public void shouldFailedForUpdateScoreForNotStartedGame() {
        int id = ScoreBoard.newGame("England", "Italy").getId();
        assertThrows(ScoreBoardException.class, () -> ScoreBoard.updateScore(id, 1, -2));
    }

    private void verifyGameScore(int id, int homeScore, int awayScore) {
        Game game = ScoreBoard.getGame(id);
        assertEquals(game.getHomeTeamScore(), homeScore);
        assertEquals(game.getAwayTeamScore(), awayScore);
    }

    private void verifyGameStarted(int id) {
        Game game = ScoreBoard.getGame(id);
        assertNotNull(game.getStartedAt());
    }

    private void verifyGameFinished(int id) {
        Game game = ScoreBoard.getGame(id);
        assertNotNull(game.getFinishedAt());
    }

    private void addGame(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        int id = ScoreBoard.newGame(homeTeam, awayTeam).getId();
        ScoreBoard.startGame(id);
        ScoreBoard.updateScore(id, homeScore, awayScore);
        ScoreBoard.finishGame(id);
    }

}
