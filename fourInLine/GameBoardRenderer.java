package fourInLine;

import java.util.stream.IntStream;

public class GameBoardRenderer {
    private static final char HORIZONTAL_LINE = '═';
    private static final char VERTICAL_LINE = '║';
    private static final char CROSS = '╬';

    public static String render(Game game) {

        return game.getCurrentState() + "\n\n" +
                generateRowHeader(game) +
                generateBoardRepresentation(game) +
                generateColumnNumbers(game);
    }

    private static String generateRowHeader(Game game) {
        String heightStr = String.valueOf(game.getHeight());
        StringBuilder rowHeaderBuilder = new StringBuilder();

        rowHeaderBuilder.append(" ".repeat(heightStr.length() + 1));
        rowHeaderBuilder.append(CROSS);

        IntStream.range(0, game.getBase()).forEach(column -> {
            rowHeaderBuilder.append(HORIZONTAL_LINE).append(HORIZONTAL_LINE).append(HORIZONTAL_LINE);
            rowHeaderBuilder.append(CROSS);
        });
        rowHeaderBuilder.append("\n");

        return rowHeaderBuilder.toString();
    }

    private static String generateBoardRepresentation(Game game) {
        StringBuilder rowBuilder = new StringBuilder();
        String heightStr = String.valueOf(game.getHeight());

        IntStream.range(0, game.getHeight()).forEach(row -> {
            int rowNumber = game.getHeight() - row;
            rowBuilder.append(rowNumber).append(" ".repeat(heightStr.length() - String.valueOf(rowNumber).length() + 1)).append(VERTICAL_LINE);
            IntStream.range(0, game.getBase()).forEach(column -> {
                rowBuilder.append(" ").append(game.getPiece(column, rowNumber - 1)).append(" ");
                rowBuilder.append(VERTICAL_LINE);
            });
            rowBuilder.append("\n");

            rowBuilder.append(" ").append(" ".repeat(heightStr.length())).append(CROSS);
            IntStream.range(0, game.getBase()).forEach(column -> {
                rowBuilder.append(HORIZONTAL_LINE).append(HORIZONTAL_LINE).append(HORIZONTAL_LINE);
                rowBuilder.append(CROSS);
            });

            rowBuilder.append("\n");
        });
        return rowBuilder.toString();
    }

    private static String generateColumnNumbers(Game game) {
        StringBuilder rowFooterBuilder = new StringBuilder();
        String heightStr = String.valueOf(game.getHeight());

        rowFooterBuilder.append(" ".repeat(heightStr.length()));
        IntStream.range(0, game.getBase()).forEach(column -> {
            rowFooterBuilder.append(" ".repeat(4 - String.valueOf(column + 1).length()));
            rowFooterBuilder.append(column + 1);
        });
        rowFooterBuilder.append("\n");

        return rowFooterBuilder.toString();
    }
}