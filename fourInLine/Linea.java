package fourInLine;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Linea {
    protected static final String message_invalid_dimensions_for_board = "Invalid dimensions for board";
    protected static final String message_cant_play_in_position = "Can't play in that position";
    static final char redPiece = 'R';
    static final char bluePiece = 'B';
    private static final char emptyPiece = ' ';
    ArrayList<ArrayList<Character>> board = new ArrayList<>();
    private int base;
    private int height;
    private GameState gameState;
    private GameMode gameMode;

    public Linea(int base, int height, char gameModeIdentifier) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException(message_invalid_dimensions_for_board);
        }

        this.base = base;
        this.height = height;
        this.gameState = new RedTurn(this);
        this.gameMode = GameMode.setGameMode(Character.toUpperCase(gameModeIdentifier));


        IntStream.range(0, this.base).forEach(i -> this.board.add(new ArrayList<>()));
    }

    private static int offsetPromptToStartInOne(int prompt) {
        return prompt - 1;
    }


    public void playRedAt(int prompt) {
        prompt = offsetPromptToStartInOne(prompt);
        checkSpaceAvailability(prompt);
        gameState.playRed(prompt);
    }

    public void playBlueAt(int prompt) {
        prompt = offsetPromptToStartInOne(prompt);
        checkSpaceAvailability(prompt);
        gameState.playBlue(prompt);
    }


    public void playPiece(int column, char pieceType) {
        board.get(column).add(pieceType);
    }

    public void setState(GameState gameState) {
        this.gameState = gameState;
    }

    private void checkSpaceAvailability(int prompt) {
        if (prompt < 0 || prompt >= base || board.get(prompt).size() >= height) {
            throw new IllegalArgumentException(message_cant_play_in_position);
        }
    }

    public char getPiece(int column, int row) {
        return IntStream.range(0, base)
                .filter(x -> x == column && isOccupied(x, row))
                .mapToObj(x -> board.get(x).get(row))
                .findFirst()
                .orElse(emptyPiece);
    }

    private boolean isOccupied(int x, int y) {
        return x < base && y < height && y < board.get(x).size() && y >= 0 && x >= 0;
    }

    public boolean checkVerticalWin(char pieceType) {
        return IntStream.range(0, base)
                .anyMatch(i -> IntStream.range(0, height - 3)
                        .anyMatch(j -> IntStream.range(0, 4)
                                .allMatch(k -> getPiece(i, j + k) == pieceType)));
    }

    public boolean checkHorizontalWin(char pieceType) {
        return IntStream.range(0, base - 3)
                .anyMatch(i -> IntStream.range(0, height)
                        .anyMatch(j -> IntStream.range(0, 4)
                                .allMatch(k -> getPiece(i + k, j) == pieceType)));
    }

    public boolean checkDiagonalWin(char pieceType) {
        boolean leftToRightDiagonal = IntStream.range(0, base - 3)
                .anyMatch(startX -> IntStream.range(0, height - 3)
                        .anyMatch(startY -> IntStream.range(0, 4)
                                .allMatch(offset -> getPiece(startX + offset, startY + offset) == pieceType)));

        boolean rightToLeftDiagonal = IntStream.range(3, base)
                .anyMatch(startX -> IntStream.range(0, height - 3)
                        .anyMatch(startY -> IntStream.range(0, 4)
                                .allMatch(offset -> startX - offset >= 0 && startY + offset < height
                                        && getPiece(startX - offset, startY + offset) == pieceType)));


        return leftToRightDiagonal || rightToLeftDiagonal;
    }

    public boolean finished() {
        return gameState.isFinished();
    }

    public String show() {
        return LineaBoardRenderer.render(this);
    }

    public boolean isRedTurn() {
        return gameState.isRedTurn();
    }

    public boolean isBlueTurn() {
        return gameState.isBlueTurn();
    }

    public boolean BlueWins() {
        return gameMode.checkWin(this, bluePiece);
    }

    public boolean RedWins() {
        return gameMode.checkWin(this, redPiece);
    }

    public boolean isDraw() {
        return boardIsFull() && !BlueWins() && !RedWins();
    }

    public boolean boardIsFull() {
        return IntStream.range(0, base)
                .allMatch(i -> board.get(i).size() == height);
    }

    public int getBase() {
        return base;
    }

    public int getHeight() {
        return height;
    }

    public String getCurrentState() {
        return gameState.getCurrentState();
    }
}