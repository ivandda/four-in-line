package fourInLine.gameMode;

import fourInLine.Game;

public class Diagonals extends GameMode {
    public Diagonals() {
        identifier = 'B';
    }

    @Override
    public boolean checkWin(Game game, char pieceType) {
        return game.checkDiagonalWin(pieceType);
    }
}
