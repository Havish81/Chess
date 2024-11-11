package game;

import board.ConsoleChessBoard;
import java.util.Scanner;
import piece.PieceColor;
import piece.position.PieceMove;
import piece.position.PiecePosition;

/**
 * The `Game` class represents a chess game with functionality for player moves,
 * check, and checkmate detection.
 */
public class ConsoleGame extends Game {

    private final Scanner scanner;

    /**
     * The Game constructor initializes a chess game and sets the current player to white.
     */
    public ConsoleGame() {
        super();
        
        board = new ConsoleChessBoard();
        scanner = new Scanner(System.in);
    }

    /**
     * The `start` function in Java implements a turn-based chess game where
     * players can make moves, check for check and checkmate, and switch turns
     * until the game ends.
     */
    public void start() {
        while (true) {
            // Display the board
            board.display();

            // Prompt for user input
            System.out.printf("Player: %s. Enter your move (e. g. a2 a4) or Q to exit: ", currentPlayer.getName());
            String move = scanner.nextLine();

            // If the user asked to quite than terminate the program
            if (canQuit(move)) {
                break;
            }

            PieceMove pieceMove = parseMove(move);

            if (pieceMove != null) {
                board.movePiece(pieceMove);
                System.out.println("You moved: " + move);

                if (board.isCheck(whitePlayer)) {
                    System.out.println("White is in check.");
                }

                if (board.isCheck(blackPlayer)) {
                    System.out.println("White is in check.");
                }

                if (board.isCheckmate(whitePlayer)) {
                    System.out.println("White is in checkmate. Black is a winner.");
                    break;
                }

                if (board.isCheck(blackPlayer)) {
                    System.out.println("Black is in checkmate. White is a winner.");
                    break;
                }

                if (currentPlayer.getColor() == PieceColor.White) {
                    currentPlayer = blackPlayer;
                } else {
                    currentPlayer = whitePlayer;
                }
            } else {
                System.out.println("Invalid move, try again."); // Prompt for another input if invalid
            }
        }
    }

    private PieceMove parseMove(String move) {
        if (move.matches("[a-h][1-8] [a-h][1-8]")) {
            PiecePosition from = new PiecePosition(7 - (move.charAt(1) - '1'), move.charAt(0) - 'a');
            PiecePosition to = new PiecePosition(7 - (move.charAt(4) - '1'), move.charAt(3) - 'a');
            return new PieceMove(from, to);
        } else {
            return null;
        }
    }

    private boolean canQuit(String move) {
        return "Q".equals(move) || "q".equals(move);
    }
}
