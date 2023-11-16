package fourInLine.gameState;

import fourInLine.Line;

public class RedWon extends GameState {
    public RedWon(Line game) {
        super(game);
    }

    @Override
    public void playRed(int column) {
        throw new RuntimeException(redCantPlayMessage);
    }

    @Override
    public void playBlue(int column) {
        throw new RuntimeException(blueCantPlayMessage);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isRedTurn() {
        return false;
    }

    @Override
    public boolean isBlueTurn() {
        return false;
    }

    @Override
    public boolean isNextTurn() {
        return game.RedWins();
    }

    @Override
    public String getCurrentState() {
        return "Game ended: Red won";
    }
}
