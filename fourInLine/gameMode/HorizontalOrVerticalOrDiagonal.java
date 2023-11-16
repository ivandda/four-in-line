package fourInLine.gameMode;

import fourInLine.Game;

public class HorizontalOrVerticalOrDiagonal extends GameMode {
    public HorizontalOrVerticalOrDiagonal() {
        identifier = 'C';
    }

    @Override
    public boolean checkWin(Game game, char pieceType) {
        return game.checkVerticalWin(pieceType) || game.checkHorizontalWin(pieceType) || game.checkDiagonalWin(pieceType);
    }
}
