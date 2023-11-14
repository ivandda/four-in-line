package fourInLine;

import java.util.stream.IntStream;

public class LineaBoardRenderer {
    private static final char HORIZONTAL_LINE = '═';
    private static final char VERTICAL_LINE = '║';
    private static final char CROSS = '╬';

    public static String render(Linea linea) {
        StringBuilder boardString = new StringBuilder();

        boardString.append(linea.getCurrentState()).append("\n\n");
        boardString.append(generateRowHeader(linea));
        boardString.append(generateBoardRepresentation(linea));
        boardString.append(generateColumnNumbers(linea));

        return boardString.toString();
    }

    private static String generateRowHeader(Linea linea) {
        String heightStr = String.valueOf(linea.getHeight());
        StringBuilder rowHeaderBuilder = new StringBuilder();

        rowHeaderBuilder.append(" ".repeat(heightStr.length() + 1));
        rowHeaderBuilder.append(CROSS);

        IntStream.range(0, linea.getBase()).forEach(column -> {
            rowHeaderBuilder.append(HORIZONTAL_LINE).append(HORIZONTAL_LINE).append(HORIZONTAL_LINE);
            rowHeaderBuilder.append(CROSS);
        });
        rowHeaderBuilder.append("\n");

        return rowHeaderBuilder.toString();
    }

    private static String generateBoardRepresentation(Linea linea) {
        StringBuilder rowBuilder = new StringBuilder();
        String heightStr = String.valueOf(linea.getHeight());

        IntStream.range(0, linea.getHeight()).forEach(row -> {
            int rowNumber = linea.getHeight() - row;
            rowBuilder.append(rowNumber).append(" ".repeat(heightStr.length() - String.valueOf(rowNumber).length() + 1)).append(VERTICAL_LINE);
            IntStream.range(0, linea.getBase()).forEach(column -> {
                rowBuilder.append(" ").append(linea.getPiece(column, rowNumber - 1)).append(" ");
                rowBuilder.append(VERTICAL_LINE);
            });
            rowBuilder.append("\n");

            rowBuilder.append(" ").append(" ".repeat(heightStr.length())).append(CROSS);
            IntStream.range(0, linea.getBase()).forEach(column -> {
                rowBuilder.append(HORIZONTAL_LINE).append(HORIZONTAL_LINE).append(HORIZONTAL_LINE);
                rowBuilder.append(CROSS);
            });

            rowBuilder.append("\n");
        });
        return rowBuilder.toString();
    }

    private static String generateColumnNumbers(Linea linea) {
        StringBuilder rowFooterBuilder = new StringBuilder();
        String heightStr = String.valueOf(linea.getHeight());

        rowFooterBuilder.append(" ".repeat(heightStr.length()));
        IntStream.range(0, linea.getBase()).forEach(column -> {
            rowFooterBuilder.append(" ".repeat(4 - String.valueOf(column + 1).length()));
            rowFooterBuilder.append(column + 1);
        });
        rowFooterBuilder.append("\n");

        return rowFooterBuilder.toString();
    }
}