
import board.*;
import java.util.Scanner;
import piece.PieceColor;
import piece.position.PieceMove;
import piece.position.PiecePosition;
import piece.position.PiecePositionColumn;
import piece.position.PiecePositionRow;
import player.Player;

public class Game {

    private final ConsoleChessBoard board;
    private final Scanner scanner;

    private final Player whitePlayer;
    private final Player blackPlayer;
    private Player currentPlayer;

    public Game() {
        board = new ConsoleChessBoard();
        scanner = new Scanner(System.in);

        whitePlayer = new Player("White", PieceColor.White);
        blackPlayer = new Player("Black", PieceColor.Black);
        currentPlayer = whitePlayer;
    }

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
            PiecePosition from = new PiecePosition(PiecePositionRow.Row7, PiecePositionColumn.A);
            PiecePosition to = new PiecePosition(PiecePositionRow.Row6, PiecePositionColumn.A);
            return new PieceMove(from, to);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        new Game().start(); // Start the game
    }

    private boolean canQuit(String move) {
        return "Q".equals(move) || "q".equals(move);
    }
}
