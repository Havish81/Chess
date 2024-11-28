package game;

import board.ChessBoard;
import piece.PieceColor;
import player.Player;

/**
 * The `Game` class represents a chess game with functionality for player moves,
 * check, and checkmate detection. This class contains the common game logic,
 * which can be extended for different game interfaces (Console, GUI, etc.).
 */
public abstract class Game {

    protected ChessBoard board;
    protected Player whitePlayer;
    protected Player blackPlayer;
    protected Player currentPlayer;

    /**
     * The Game constructor initializes a chess game and sets the current player
     * to white.
     */
    public Game() {
        whitePlayer = new Player("White", PieceColor.White);
        blackPlayer = new Player("Black", PieceColor.Black);
        currentPlayer = whitePlayer;
    }

    /**
     * The `start` method implements a turn-based chess game where players can
     * make moves, check for check and checkmate, and switch turns until the
     * game ends.
     */
    public void start() {
        while (!isGameOver()) {
            // Display the board (interface-dependent, implemented in subclass)
            displayBoard();

            // Prompt for user input (interface-dependent, implemented in subclass)
            String move = getPlayerMove();
            if (canQuit(move)) {
                break;
            }

            // Parse and execute the move
            if (processMove(move)) {
                checkForCheckOrCheckmate();
                toggleTurn();
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
        System.out.println("Game over!");
    }

    /**
     * Abstract method to display the board. Each subclass must implement this method.
     */
    protected abstract void displayBoard();

    /**
     * Abstract method to get the player's move. Each subclass must implement this method.
     */
    protected abstract String getPlayerMove();

    /**
     * Abstract method to handle quitting. Each subclass must implement this method.
     */
    protected abstract boolean canQuit(String move);

    /**
     * Process the move. Returns true if the move is valid, false otherwise.
     */
    protected boolean processMove(String move) {
        return currentPlayer.makeMove(board, move);
    }

    /**
     * Abstract method to check for check or checkmate. Each subclass can implement it differently.
     */
    protected abstract void checkForCheckOrCheckmate();

    /**
     * Switches the current player.
     */
    protected void toggleTurn() {
        currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
    }

    /**
     * Checks if the game is over (checkmate, stalemate, or other conditions).
     *
     * @return true if the game is over, false otherwise.
     */
    protected boolean isGameOver() {
        return isCheckmate() || isStalemate();
    }

    /**
     * Placeholder method to determine if the game is in a checkmate state.
     *
     * @return true if checkmate, false otherwise.
     */
    protected abstract boolean isCheckmate();

    /**
     * Placeholder method to determine if the game is in a stalemate state.
     *
     * @return true if stalemate, false otherwise.
     */
    protected abstract boolean isStalemate();
}
