package al.scoreboard.model.sequence;

public class GameSequence {

    private static GameSequence instance;

    public static int next() {
        return getInstance().nextValue();
    }

    public static void reset() {
        instance = new GameSequence();
    }

    private static GameSequence getInstance() {
        if (instance == null)
            instance = new GameSequence();

        return instance;
    }

    private int value;

    private GameSequence() {
        this.value = 0;
    }

    private int nextValue() {
        return value++;
    }

}
