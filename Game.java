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

            // Use the move variable here
            if (isValidMove(move)) {
                makeMove(move); // Execute the move if valid
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
