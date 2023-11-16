package fourInLine.gameMode;

import fourInLine.Game;

import java.util.ArrayList;

public abstract class GameMode {
    public static final String invalid_game_mode_choice = "Invalid game mode reference";
    private static final ArrayList<GameMode> possibleGameModes = new ArrayList<>(java.util.Arrays.asList(
            new HorizontalOrVertical(),
            new Diagonals(),
            new HorizontalOrVerticalOrDiagonal()
    ));
    protected char identifier;

    public static GameMode setGameMode(char choice) {
        return possibleGameModes.stream()
                .filter(gameMode -> gameMode.applies(choice))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(invalid_game_mode_choice));
    }

    public boolean applies(char gameModeIdentifier) {
        return identifier == gameModeIdentifier;
    }

    public abstract boolean checkWin(Game game, char bluePiece);
}
