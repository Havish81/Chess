
import game.ConsoleGame;
import game.GUIGame;

/**
 * The Chess class contains the main method to start the game by creating a new
 * Game object and calling its start method.
 */
public class Chess {

    /**
     * The main function creates a new Game object and starts the game.
     */
    public static void main(String[] args) {
        // Start the game
        if (args.length > 0 && "console".equals(args[0])) {
            new ConsoleGame().start();
        } else {
            new GUIGame().start();
        }
    }
}
