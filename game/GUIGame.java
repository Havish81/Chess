package game;

import board.GUIChessBoard;
import piece.PieceColor;
import piece.position.PieceMove;
import piece.position.PiecePosition;

/**
 * The `GUIGame` class represents a chess game played in a GUI-based environment.
 * It handles capturing player input and updating the board display.
 */
public class GUIGame extends Game {

    /**
     * The GUIGame constructor initializes a chess game and sets the current player to white.
     */
    public GUIGame() {
        super();
        board = new GUIChessBoard(); // Assumes GUIChessBoard extends ChessBoard with display methods
    }

    /**
     * Displays the chessboard in the GUI.
     */
    @Override
    protected void displayBoard() {
        board.display();  // Update the GUI to reflect the current state of the board
    }

    /**
     * Gets the player's move via GUI interaction.
     * This could be integrated with GUI event handling.
     *
     * @return the move made by the player as a string.
     */
    @Override
    protected String getPlayerMove() {
        // Return the move entered by the player (this needs to be connected with the GUI framework)
        return ""; // Placeholder for actual GUI interaction (e.g., mouse clicks or button presses)
    }

    /**
     * Determines if the player wants to quit the game.
     *
     * @param move the move entered by the player.
     * @return true if the player wants to quit, false otherwise.
     */
    @Override
    protected boolean canQuit(String move) {
        return "Q".equalsIgnoreCase(move); // Example: "Q" to quit
    }

    /**
     * Checks if the current player is in check or checkmate.
     * Updates the GUI accordingly and ends the game if necessary.
     */
    @Override
    protected void checkForCheckOrCheckmate() {
        if (board.isCheck(currentPlayer)) {
            System.out.println(currentPlayer.getName() + " is in check.");
        }

        if (board.isCheckmate(currentPlayer)) {
            System.out.println(currentPlayer.getName() + " is in checkmate. " +
                (currentPlayer.getColor() == PieceColor.White ? "Black" : "White") + " wins!");
            System.exit(0);  // End the game after checkmate
        }
    }

    /**
     * Parses the move string (e.g., "a2 a4") into a PieceMove object.
     * This method will need to work with the GUI interaction logic.
     */
    private PieceMove parseMove(String move) {
        if (move.matches("[a-h][1-8] [a-h][1-8]")) {
            PiecePosition from = new PiecePosition(7 - (move.charAt(1) - '1'), move.charAt(0) - 'a');
            PiecePosition to = new PiecePosition(7 - (move.charAt(4) - '1'), move.charAt(3) - 'a');
            return new PieceMove(from, to);
        } else {
            return null;
        }
    }

    /**
     * Checks if the game is in a checkmate state.
     *
     * @return true if checkmate, false otherwise.
     */
    @Override
    protected boolean isCheckmate() {
        return board.isCheckmate(currentPlayer);
    }

    /**
     * Checks if the game is in a stalemate state.
     *
     * @return true if stalemate, false otherwise.
     */
    @Override
    protected boolean isStalemate() {
        return board.isStalemate(currentPlayer);
    }
}
