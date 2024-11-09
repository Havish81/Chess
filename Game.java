
import chessboard.*;
import chesspiece.PieceColor;
import java.util.Scanner;

public class Game {

    private final ConsoleChessBoard board;
    private final Scanner scanner;

    private Player whitePlayer;
    private Player blackPlayer;
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
            System.out.printf("Player: %s. Enter your move (e.g., e4) or Q to exit: ", currentPlayer.getName());
            String move = scanner.nextLine();

            // If the user asked to quite than terminate the program
            if ("Q".equals(move) || "q".equals(move)) {
                break;
            }

            // Use the move variable here
            if (isValidMove(move)) {
                makeMove(move); // Execute the move if valid

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

    private boolean isValidMove(String move) {
        // Implement your logic to validate the move
        // For example, checking the format of the move (e.g., e4, g5, etc.)
        // For now, let's just return true to proceed
        return move.matches("[a-h][1-8]"); // Basic validation: e.g., e4
    }

    private void makeMove(String move) {
        // Implement your logic to update the board state based on the move
        // Example: move a piece from the specified position
        System.out.println("You moved: " + move); // Display the move for confirmation
        // Update your board here based on the move
    }

    public static void main(String[] args) {
        new Game().start(); // Start the game
    }
}
