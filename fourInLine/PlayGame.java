package fourInLine;

public class PlayGame {

    public static void main(String[] args) throws Exception {

        System.out.println("Dimensions?");

        Game game = new Game(promptAsInt("Base? "),
                promptAsInt("Height? "),
                promptAsChar("Game Mode: A, B o C? "));

        System.out.println(game.show());

        while (!game.finished()) {
            game.playRedAt(promptAsInt("Red? "));
            System.out.println(game.show());

            if (!game.finished()) {
                game.playBlueAt(promptAsInt("Blue? "));
                System.out.println(game.show());
            }
        }
    }


    private static int promptAsInt(String message) {
        System.out.print(message);
        return Integer.parseInt(System.console().readLine());

    }


    private static char promptAsChar(String message) {
        System.out.print(message);
        return System.console().readLine().charAt(0);

    }

}
