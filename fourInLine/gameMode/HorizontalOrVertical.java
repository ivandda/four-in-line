package fourInLine.gameMode;

import fourInLine.Game;

public class HorizontalOrVertical extends GameMode {
    public HorizontalOrVertical() {
        identifier = 'A';
    }

    @Override
    public boolean checkWin(Game game, char pieceType) {
        return game.checkVerticalWin(pieceType) || game.checkHorizontalWin(pieceType);
    }
}
