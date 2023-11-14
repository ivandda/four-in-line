package fourInLine;

public class BlueTurn extends GameState {
    public BlueTurn(Linea game) {
        super(game);
    }

    @Override
    public void playRed(int column) {
        throw new RuntimeException(redCantPlayMessage);
    }

    @Override
    public void playBlue(int column) {
        game.playPiece(column, Linea.bluePiece);
        game.setState(getNextState());
    }


    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isRedTurn() {
        return false;
    }

    @Override
    public boolean isBlueTurn() {
        return true;
    }

    @Override
    public boolean isNextTurn() {
        return !game.isDraw() && !game.BlueWins() && !game.RedWins() && !game.isBlueTurn() && game.isRedTurn();
    }

    @Override
    public String getCurrentState() {
        return "Blue's turn";
    }
}
