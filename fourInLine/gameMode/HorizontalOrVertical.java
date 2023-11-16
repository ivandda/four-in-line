package fourInLine.gameMode;

import fourInLine.Line;

public class HorizontalOrVertical extends GameMode {
    public HorizontalOrVertical() {
        identifier = 'A';
    }

    @Override
    public boolean checkWin(Line line, char pieceType) {
        return line.checkVerticalWin(pieceType) || line.checkHorizontalWin(pieceType);
    }
}
