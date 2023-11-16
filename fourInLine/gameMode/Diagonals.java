package fourInLine.gameMode;

import fourInLine.Line;

public class Diagonals extends GameMode {
    public Diagonals() {
        identifier = 'B';
    }

    @Override
    public boolean checkWin(Line line, char pieceType) {
        return line.checkDiagonalWin(pieceType);
    }
}
