package fourInLine.gameState;

import fourInLine.Game;

public class Draw extends GameState{
    public Draw(Game game) {
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
        return game.isDraw();
//        return !game.isDraw() && !game.BlueWins() && !game.RedWins()
    }

    @Override
    public String getCurrentState() {
        return "Game ended: Draw";
    }
}
