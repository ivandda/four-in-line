package fourInLine.gameState;

import fourInLine.Game;

public class RedWon extends GameState {
    public RedWon(Game game) {
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
