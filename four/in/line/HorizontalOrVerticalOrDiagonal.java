package four.in.line;

public class HorizontalOrVerticalOrDiagonal extends GameMode {
    public HorizontalOrVerticalOrDiagonal() {
        identifier = 'C';
    }

    @Override
    public boolean checkWin(Linea linea, char pieceType) {
        return linea.checkVerticalWin(pieceType) || linea.checkHorizontalWin(pieceType) || linea.checkDiagonalWin(pieceType);
    }
}
