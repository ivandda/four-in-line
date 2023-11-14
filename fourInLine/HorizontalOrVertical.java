package fourInLine;

public class HorizontalOrVertical extends GameMode {
    public HorizontalOrVertical() {
        identifier = 'A';
    }

    @Override
    public boolean checkWin(Linea linea, char pieceType) {
        return linea.checkVerticalWin(pieceType) || linea.checkHorizontalWin(pieceType);
    }
}
