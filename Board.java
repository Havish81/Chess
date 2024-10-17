public class Board {
    private final Piece[][] board; // The board variable

    public Board() {
        board = new Piece[8][8]; // Initialize your board here
        initializeBoard(); // Call to initialize pieces on the board
    }

    private void initializeBoard() {
        // Example initialization for demonstration purposes
        board[0][0] = new Piece("Black Rook");
        board[0][1] = new Piece("Black Knight");
        board[0][2] = new Piece("Black Bishop");
        board[0][3] = new Piece("Black Queen");
        board[0][4] = new Piece("Black King");
        board[0][5] = new Piece("Black Bishop");
        board[0][6] = new Piece("Black Knight");
        board[0][7] = new Piece("Black Rook");

        // Place Black pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Piece("Black Pawn");
        }

        // Place White pieces
        board[7][0] = new Piece("White Rook");
        board[7][1] = new Piece("White Knight");
        board[7][2] = new Piece("White Bishop");
        board[7][3] = new Piece("White Queen");
        board[7][4] = new Piece("White King");
        board[7][5] = new Piece("White Bishop");
        board[7][6] = new Piece("White Knight");
        board[7][7] = new Piece("White Rook");

        // Place White pawns
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Piece("White Pawn");
        }
    }

    public void display() {
        // Code to display the board (for example, print the pieces)
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].getColor() + " "); // Use getColor() to display the piece
                } else {
                    System.out.print(". "); // Empty square
                }
            }
            System.out.println();
        }
    }

    // Additional methods for Board can be added here, e.g., movePiece, etc.
}
