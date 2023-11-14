package four.in.line;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.stream.IntStream;


import static four.in.line.Linea.message_cant_play_in_position;
import static four.in.line.Linea.message_invalid_dimensions_for_board;
import static org.junit.jupiter.api.Assertions.*;


public class LineaTests {
    private Linea game;

    @BeforeEach
    public void setUp() {
        game = new Linea(4, 4, 'A');
    }

    @Test
    public void test01CreationOfDifferentBoardDimensions1() {
        Linea game = new Linea(1, 2, 'A');
        assertEquals(1, game.getBase());
        assertEquals(2, game.getHeight());
    }

    @Test
    public void tes02tCreationOfDifferentBoardDimensions2() {
        Linea game = new Linea(4, 5, 'A');
        assertEquals(4, game.getBase());
        assertEquals(5, game.getHeight());
    }

    @Test
    public void test03CreationOfDifferentBoardDimensions3() {
        Linea game = new Linea(9,15, 'A');
        assertEquals(9, game.getBase());
        assertEquals(15, game.getHeight());
    }

    @Test
    public void test04CreationOfDifferentBoardDimensions4() {
        Linea game = new Linea(1,21, 'A');
        assertEquals(1, game.getBase());
        assertEquals(21, game.getHeight());
    }

    @Test
    public void test06CannotInitializeBoardSmallerThan1x1() {
        assertThrowsError(() -> new Linea(0, 0, 'A'), message_invalid_dimensions_for_board);
    }

    @Test
    public void test02CannotInitializeBoardWithNegativeDimensions() {
        assertThrowsError(() -> new Linea(-4, -4, 'A'), message_invalid_dimensions_for_board);
    }

    @Test
    public void test03CannotInitializeGameWithInvalidGameMode() {
        assertThrowsError(() -> new Linea(4, 4, 'D'), GameMode.invalid_game_mode_choice);
    }

    @Test
    public void test04GameIsInitializedCorrectly() {
        IntStream.range(0, 4).forEach(column -> {
            IntStream.range(0, 4).forEach(row -> {
                assertEquals(' ', game.getPiece(column, row));
            });
        });
    }


    @Test
    public void test04RedStartsGame() {
        assertTrue(game.isRedTurn());
    }

    @Test
    public void testBlueCantPlayInFirstRound() {
        assertThrowsError(() -> game.playBlueAt(1), GameState.blueCantPlayMessage);
    }

    @Test
    public void BluePLaysAfterRed() {
        game.playRedAt(1);
        assertTrue(game.isBlueTurn());
    }

    @Test
    void RedPlaysAfterBlue() {
        game.playRedAt(1);
        game.playBlueAt(1);
        assertTrue(game.isRedTurn());
    }

    @Test
    void redCantPLayInBlueTurn() {
        game.playRedAt(1);
        assertThrowsError(() -> game.playRedAt(1), GameState.redCantPlayMessage);
    }

    @Test
    void blueCantPLayInRedTurn() {
        game.playRedAt(1);
        game.playBlueAt(1);
        assertThrowsError(() -> game.playBlueAt(1), GameState.blueCantPlayMessage);
    }

    @Test
    public void test05CannotPlayOutsideBoard() {
        assertThrowsError(() -> game.playRedAt(5), message_cant_play_in_position);
    }

    @Test
    public void test06CannotPlayInColumn0() {
        assertThrowsError(() -> game.playRedAt(0), message_cant_play_in_position);
    }

    @Test
    public void playInColumnOnePlaysInOneForOffsetPurposes() {
        game.playRedAt(1);
        assertEquals('R', game.getPiece(0, 0));
    }

    @Test
    public void test07PrintsEmptyBoard() {
        String boardRenderExpected = """
                Red's turn
                            
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
        assertEquals(boardRenderExpected, game.show());
    }

    @Test
    public void test08PrintsRedPieces() {
        game.playRedAt(2);
        String boardRenderExpected = """
                Blue's turn
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║   ║ R ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
        assertEquals(boardRenderExpected, game.show());
    }

    @Test
    public void test09PrintsBluePieces() {
        game.playRedAt(2);
        game.playBlueAt(2);
        String boardRenderExpected = """
                Red's turn
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║   ║ B ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║   ║ R ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
        assertEquals(boardRenderExpected, game.show());
    }

    @Test
    public void test10PrintsRedAndBluePieces() {
        playLinea(2, 2, 3, 3);
        String boardRenderExpected = """
                Red's turn
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║   ║ B ║ B ║   ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║   ║ R ║ R ║   ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
        assertEquals(boardRenderExpected, game.show());
    }

    @Test
    public void test11AlternateTurnsAndPlacePieces() {
        playLinea(2, 2, 3, 3, 2, 2, 3, 3);
        String boardRenderExpected = """
                Red's turn
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║ B ║ B ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║   ║ R ║ R ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║   ║ B ║ B ║   ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║   ║ R ║ R ║   ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
        assertEquals(boardRenderExpected, game.show());
    }

    @Test
    public void test12CannotPlayInFullColumn() {
        playLinea(2, 2, 2, 2);
        assertThrowsError(() -> game.playRedAt(2), message_cant_play_in_position);
    }

    @Test
    public void test13PiecesArePositionedAtTheLowestAvailablePosition() {
        playLinea(1, 2, 3, 4, 4, 3, 2, 1);
        String boardRenderExpected = """
                Red's turn
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║ B ║ R ║ B ║ R ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ R ║ B ║ R ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
        assertEquals(boardRenderExpected, game.show());
    }

    @Test
    public void test14RedWinsWithHorizontalLineModeA() {
        redPlaysHorizontalLine();
        assertEquals(redHasHorizontalLineWins(), game.show());
    }

    @Test
    public void cantPLayAfterAWin() {
        redPlaysHorizontalLine();
        assertThrowsError(() -> game.playRedAt(3), GameState.redCantPlayMessage);
    }

    @Test
    public void cantPLayAfterAWin2() {
        bluePlaysHorizontalLine();
        assertThrowsError(() -> game.playRedAt(3), GameState.redCantPlayMessage);
    }

    @Test
    public void gameDraws(){
        playLinea(1, 2, 3, 4, 1, 3, 2, 2, 1, 1, 4, 3, 2, 4, 3, 4);
        assertTrue(game.isDraw());
    }

    @Test
    public void cantPlayAfterADraw() {
        Linea game = new Linea(2, 2, 'A');
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(2);
        assertThrowsError(() -> game.playRedAt(2), message_cant_play_in_position);
    }

    @Test
    public void test15BlueWinsWithHorizontalLineModeA() {
        bluePlaysHorizontalLine();
        assertEquals(blueHasHorizontalLineWins(), game.show());
    }

    @Test
    public void test16RedWinsWithVerticalLineModeA() {
        redPlaysVerticalLine();
        assertEquals(redHasVerticalLineWins(), game.show());
    }

    @Test
    public void test17BlueWinsWithVerticalLineModeA() {
        bluePlaysVerticalLine();
        assertEquals(blueHasVerticalLineWins(), game.show());
    }

    @Test
    public void test18RedDoesntWinWithDiagonalLineModeA() {
        redPlaysDiagonalLine();
        assertEquals(redHasDiagonalLineNotWins(), game.show());
    }

    @Test
    public void test19BlueDoesntWinWithDiagonalLineModeA() {
        bluePlaysDiagonalLine();
        assertEquals(blueHasDiagonalLineNotWins(), game.show());
    }

    @Test
    public void test20RedDoesntWinWithHorizontalLineModeB() {
        game = newLineaB();
        redPlaysHorizontalLine();
        assertEquals(redHasHorizontalLineNotWins(), game.show());
    }

    @Test
    public void test21BlueDoesntWinWithHorizontalLineModeB() {
        game = newLineaB();
        bluePlaysHorizontalLine();
        assertEquals(blueHasHorizontalLineNotWins(), game.show());
    }

    @Test
    public void test22RedDoesntWinWithVerticalLineModeB() {
        game = newLineaB();
        redPlaysVerticalLine();
        assertEquals(redHasVerticalLineNotWins(), game.show());
    }

    @Test
    public void test23BlueDoesntWinWithVerticalLineModeB() {
        game = newLineaB();
        bluePlaysVerticalLine();
        assertEquals(blueHasVerticalLineNotWins(), game.show());
    }

    @Test
    public void test24RedWinsWithDiagonalLineModeB() {
        game = newLineaB();
        redPlaysDiagonalLine();
        assertEquals(redHasDiagonalLineWins(), game.show());
    }

    @Test
    public void test25BlueWinsWithDiagonalLineModeB() {
        game = newLineaB();
        bluePlaysDiagonalLine();
        assertEquals(blueHasDiagonalLineWins(), game.show());
    }

    @Test
    public void test26RedWinsWithHorizontalLineModeC() {
        game = newLineaC();
        redPlaysHorizontalLine();
        assertEquals(redHasHorizontalLineWins(), game.show());
    }

    @Test
    public void test27BlueWinsWithHorizontalLineModeC() {
        game = newLineaC();
        bluePlaysHorizontalLine();
        assertEquals(blueHasHorizontalLineWins(), game.show());
    }

    @Test
    public void test28RedWinsWithVerticalLineModeC() {
        game = newLineaC();
        redPlaysVerticalLine();
        assertEquals(redHasVerticalLineWins(), game.show());
    }

    @Test
    public void test29BlueWinsWithVerticalLineModeC() {
        game = newLineaC();
        bluePlaysVerticalLine();
        assertEquals(blueHasVerticalLineWins(), game.show());
    }

    @Test
    public void test30RedWinsWithDiagonalLineModeC() {
        game = newLineaC();
        redPlaysDiagonalLine();
        assertEquals(redHasDiagonalLineWins(), game.show());
    }

    @Test
    public void test31BlueWinsWithDiagonalLineModeC() {
        game = newLineaC();
        bluePlaysDiagonalLine();
        assertEquals(blueHasDiagonalLineWins(), game.show());
    }


    @Test
    public void test32BlueHasDiagonalLineInverted() {
        game = newLineaB();
        playLinea(1, 2, 3, 4, 1, 3, 2, 2, 1, 1);
        String boardRenderExpected = """
                Game ended: Blue won
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║ B ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║ R ║ B ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║ R ║ R ║ B ║   ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ R ║ B ║ R ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
        assertEquals(boardRenderExpected, game.show());
    }


    @Test
    public void test33GameEndsInADraw() {
        game = newLineaC();
        playLinea(1, 2, 1, 2, 2, 1, 2, 1, 3, 4, 3, 4, 4, 3, 4, 3);
        String boardRenderExpected = """
                Game ended: Draw
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║ B ║ R ║ B ║ R ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║ B ║ R ║ B ║ R ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║ R ║ B ║ R ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ R ║ B ║ R ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
        assertEquals(boardRenderExpected, game.show());
    }

    @Test
    public void test34GameEndsWhenRedWins() {
        game = newLineaB();
        redPlaysDiagonalLine();
        assertThrowsError(() -> game.playBlueAt(3), "Blue cant play in this round");
    }

    @Test
    public void test35GameEndsWhenBlueWins() {
        game = newLineaB();
        bluePlaysDiagonalLine();
        assertThrowsError(() -> game.playRedAt(3), "Red cant play in this round");
    }

    private static String redHasHorizontalLineWins() {
        return """
                Game ended: Red won
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║ B ║ B ║ B ║   ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ R ║ R ║ R ║ R ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
    }

    private static String blueHasHorizontalLineWins() {
        return """
                Game ended: Blue won
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║ R ║ R ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║ B ║ B ║ B ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ R ║ R ║ R ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
    }

    private static String redHasHorizontalLineNotWins() {
        return """
                Blue's turn
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║ B ║ B ║ B ║   ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ R ║ R ║ R ║ R ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
    }

    private static String blueHasHorizontalLineNotWins() {
        return """
                Red's turn
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║ R ║ R ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║ B ║ B ║ B ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ R ║ R ║ R ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
    }

    private static String redHasVerticalLineWins() {
        return """
                Game ended: Red won
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║ R ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║ R ║ B ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║ R ║ B ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ R ║ B ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
    }

    private static String blueHasVerticalLineWins() {
        return """
                Game ended: Blue won
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║ B ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║ R ║ B ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║ R ║ B ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ R ║ B ║ R ║   ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
    }

    private static String redHasVerticalLineNotWins() {
        return """
                Blue's turn
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║ R ║   ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║ R ║ B ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║ R ║ B ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ R ║ B ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
    }

    private static String blueHasVerticalLineNotWins() {
        return """
                Red's turn
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║ B ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║ R ║ B ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║ R ║ B ║   ║   ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ R ║ B ║ R ║   ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
    }

    private static String redHasDiagonalLineWins() {
        return """
                Game ended: Red won
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║   ║   ║ R ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║   ║   ║ R ║ R ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║ B ║ R ║ R ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ R ║ B ║ B ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
    }

    private static String blueHasDiagonalLineWins() {
        return """
                Game ended: Blue won
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║   ║   ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║   ║   ║ B ║ R ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║   ║ B ║ R ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ B ║ R ║ R ║ R ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
    }

    private static String redHasDiagonalLineNotWins() {
        return """
                Blue's turn
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║   ║   ║ R ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║   ║   ║ R ║ R ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║ B ║ R ║ R ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ R ║ B ║ B ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
    }

    private static String blueHasDiagonalLineNotWins() {
        return """
                Red's turn
                        
                  ╬═══╬═══╬═══╬═══╬
                4 ║   ║   ║   ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                3 ║   ║   ║ B ║ R ║
                  ╬═══╬═══╬═══╬═══╬
                2 ║   ║ B ║ R ║ B ║
                  ╬═══╬═══╬═══╬═══╬
                1 ║ B ║ R ║ R ║ R ║
                  ╬═══╬═══╬═══╬═══╬
                    1   2   3   4
                """;
    }

    private void redPlaysHorizontalLine() {
        playLinea(1, 1, 2, 2, 3, 3, 4);
    }

    private void bluePlaysHorizontalLine() {
        playLinea(1, 1, 2, 2, 3, 3, 1, 4, 2, 4);
    }

    private void redPlaysVerticalLine() {
        playLinea(1, 2, 1, 2, 1, 2, 1);
    }

    private void bluePlaysVerticalLine() {
        playLinea(1, 2, 1, 2, 1, 2, 3, 2);
    }

    private void redPlaysDiagonalLine() {
        playLinea(1, 2, 2, 3, 3, 4, 3, 4, 4, 1, 4);
    }

    private void bluePlaysDiagonalLine() {
        playLinea(2, 1, 3, 2, 3, 3, 4, 4, 4, 4);
    }

    private Linea playLinea(int... moves) {
        for (int i = 0; i < moves.length; i += 2) {
            game.playRedAt(moves[i]);
            if (i + 1 == moves.length) break;
            game.playBlueAt(moves[i + 1]);
        }
        return game;
    }

    private void assertThrowsError(Executable runnable, String expectedError) {
        String actualError = assertThrows(RuntimeException.class, runnable, "Expected Error was not thrown.").getMessage();
        assertEquals(expectedError, actualError);
    }

    private Linea newLineaB() {
        return new Linea(4, 4, 'B');
    }

    private Linea newLineaC() {
        return new Linea(4, 4, 'C');
    }
}