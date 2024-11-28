package game;

import board.ConsoleChessBoard;
import piece.PieceColor;
import piece.position.PieceMove;
import piece.position.PiecePosition;

import java.util.Scanner;

/**
 * The `ConsoleGame` class represents a chess game played via the console.
 * It handles player input, displaying the board, and game-over conditions.
 */
public class ConsoleGame extends Game {

    private final Scanner scanner;

    /**
     * The ConsoleGame constructor initializes a chess game and sets the current player to white.
     */
    public ConsoleGame() {
        super();
        board = new ConsoleChessBoard();
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the chessboard in the console.
     */
    @Override
    protected void displayBoard() {
        board.display();
    }

    /**
     * Gets the player's move from the console input.
     * Returns a move string or "Q" to quit.
     */
    @Override
    protected String getPlayerMove() {
        System.out.printf("Player: %s. Enter your move (e.g., a2 a4) or Q to exit: ", currentPlayer.getName());
        return scanner.nextLine();
    }

    /**
     * Checks if the player wants to quit the game.
     */
    @Override
    protected boolean canQuit(String move) {
        return "Q".equalsIgnoreCase(move);
    }

    /**
     * Checks for check or checkmate after each move.
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
}
