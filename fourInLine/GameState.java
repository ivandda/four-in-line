package fourInLine;

import java.util.ArrayList;

public abstract class GameState {
    static Linea game;

    public GameState(Linea game) {
        this.game = game;
    }

    private static ArrayList<GameState> possibleGameStates = new ArrayList<>(java.util.Arrays.asList(
            new RedTurn(game),
            new BlueTurn(game),
            new RedWon(game),
            new BlueWon(game),
            new Draw(game)
    ));
    protected static String blueCantPlayMessage = "Blue cant play in this round";
    protected static String redCantPlayMessage = "Red cant play in this round";

    public abstract void playRed(int column);

    public abstract void playBlue(int column);

    public abstract boolean isFinished();

    public abstract boolean isRedTurn();

    public abstract boolean isBlueTurn();

    public abstract boolean isNextTurn();

    public abstract String getCurrentState();

    public GameState getNextState() {
        return possibleGameStates.stream().filter(gameState -> gameState.isNextTurn()).findFirst().get();
    }


}
