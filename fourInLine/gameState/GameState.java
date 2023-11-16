package fourInLine.gameState;

import fourInLine.Line;

import java.util.ArrayList;

public abstract class GameState {
    static Line game;

    public GameState(Line game) {
        this.game = game;
    }

    private static ArrayList<GameState> possibleGameStates = new ArrayList<>(java.util.Arrays.asList(
            new RedTurn(game),
            new BlueTurn(game),
            new RedWon(game),
            new BlueWon(game),
            new Draw(game)
    ));
    public static String blueCantPlayMessage = "Blue cant play in this round";
    public static String redCantPlayMessage = "Red cant play in this round";

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
