import java.util.Scanner;

public class Game {
    private final Board board; // Declare as final
    private final Scanner scanner; // Declare as final

    public Game() {
        board = new Board(); // Initialize the board
        scanner = new Scanner(System.in); // Initialize the scanner
    }

    public void start() {
        while (true) {
            board.display(); // Display the board state
            System.out.print("Enter your move (e.g., e4): "); // Prompt for user input
            String move = scanner.nextLine(); // Get user input

            if (isValidMove(move)) {
                makeMove(move); // Execute the move if valid
            } else {
                System.out.println("Invalid move, try again."); // Prompt for another input if invalid
            }
        }
    }

    private boolean isValidMove(String move) {
        return move.matches("[a-h][1-8]"); // Basic validation: e.g., e4
    }

    private void makeMove(String move) {
        int sourceRow = 8 - Character.getNumericValue(move.charAt(1)); // Converts '1'-'8' to 7-0
        int sourceCol = move.charAt(0) - 'a'; // Converts 'a'-'h' to 0-7

        // Here you should add your logic to move the piece and update the board
        if (board.getPiece(sourceRow, sourceCol) != null) {
            System.out.println("You moved: " + move); // Display the move for confirmation
            
            // Here we assume moving to the same position for simplicity, modify for your logic
            board.movePiece(sourceRow, sourceCol, sourceRow - 1, sourceCol); // Example move to the row above
        } else {
            System.out.println("No piece at " + move + ". Try again.");
        }
    }

    public static void main(String[] args) {
        new Game().start(); // Start the game
    }
}
