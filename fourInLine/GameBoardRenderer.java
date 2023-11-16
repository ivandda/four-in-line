package fourInLine;

import java.util.stream.IntStream;

public class GameBoardRenderer {
    private static final char HORIZONTAL_LINE = '═';
    private static final char VERTICAL_LINE = '║';
    private static final char CROSS = '╬';

    public static String render(Line line) {
        StringBuilder boardString = new StringBuilder();

        boardString.append(line.getCurrentState()).append("\n\n");
        boardString.append(generateRowHeader(line));
        boardString.append(generateBoardRepresentation(line));
        boardString.append(generateColumnNumbers(line));

        return boardString.toString();
    }

    private static String generateRowHeader(Line line) {
        String heightStr = String.valueOf(line.getHeight());
        StringBuilder rowHeaderBuilder = new StringBuilder();

        rowHeaderBuilder.append(" ".repeat(heightStr.length() + 1));
        rowHeaderBuilder.append(CROSS);

        IntStream.range(0, line.getBase()).forEach(column -> {
            rowHeaderBuilder.append(HORIZONTAL_LINE).append(HORIZONTAL_LINE).append(HORIZONTAL_LINE);
            rowHeaderBuilder.append(CROSS);
        });
        rowHeaderBuilder.append("\n");

        return rowHeaderBuilder.toString();
    }

    private static String generateBoardRepresentation(Line line) {
        StringBuilder rowBuilder = new StringBuilder();
        String heightStr = String.valueOf(line.getHeight());

        IntStream.range(0, line.getHeight()).forEach(row -> {
            int rowNumber = line.getHeight() - row;
            rowBuilder.append(rowNumber).append(" ".repeat(heightStr.length() - String.valueOf(rowNumber).length() + 1)).append(VERTICAL_LINE);
            IntStream.range(0, line.getBase()).forEach(column -> {
                rowBuilder.append(" ").append(line.getPiece(column, rowNumber - 1)).append(" ");
                rowBuilder.append(VERTICAL_LINE);
            });
            rowBuilder.append("\n");

            rowBuilder.append(" ").append(" ".repeat(heightStr.length())).append(CROSS);
            IntStream.range(0, line.getBase()).forEach(column -> {
                rowBuilder.append(HORIZONTAL_LINE).append(HORIZONTAL_LINE).append(HORIZONTAL_LINE);
                rowBuilder.append(CROSS);
            });

            rowBuilder.append("\n");
        });
        return rowBuilder.toString();
    }

    private static String generateColumnNumbers(Line line) {
        StringBuilder rowFooterBuilder = new StringBuilder();
        String heightStr = String.valueOf(line.getHeight());

        rowFooterBuilder.append(" ".repeat(heightStr.length()));
        IntStream.range(0, line.getBase()).forEach(column -> {
            rowFooterBuilder.append(" ".repeat(4 - String.valueOf(column + 1).length()));
            rowFooterBuilder.append(column + 1);
        });
        rowFooterBuilder.append("\n");

        return rowFooterBuilder.toString();
    }
}