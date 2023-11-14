package four.in.line;

public class RedTurn extends GameState {
    public RedTurn(Linea game) {
        super(game);
    }

    @Override
    public void playRed(int column) {
        game.playPiece(column, Linea.redPiece);
        game.setState(getNextState());
    }

    @Override
    public void playBlue(int column) {
        throw new RuntimeException(blueCantPlayMessage);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isRedTurn() {
        return true;
    }

    @Override
    public boolean isBlueTurn() {
        return false;
    }

    @Override
    public boolean isNextTurn() {
        return !game.isDraw() && !game.BlueWins() && !game.RedWins() && !game.isRedTurn() && game.isBlueTurn();
    }

    @Override
    public String getCurrentState() {
        return "Red's turn";
    }
}
