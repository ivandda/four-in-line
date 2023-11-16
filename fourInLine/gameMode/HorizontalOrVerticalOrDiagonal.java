package fourInLine.gameMode;

import fourInLine.Line;

public class HorizontalOrVerticalOrDiagonal extends GameMode {
    public HorizontalOrVerticalOrDiagonal() {
        identifier = 'C';
    }

    @Override
    public boolean checkWin(Line line, char pieceType) {
        return line.checkVerticalWin(pieceType) || line.checkHorizontalWin(pieceType) || line.checkDiagonalWin(pieceType);
    }
}
