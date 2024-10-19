public class Board {
    private final Piece[][] board; // The board variable

    public Board() {
        board = new Piece[8][8]; // Initialize your board here
        initializeBoard(); // Call to initialize pieces on the board
    }

    private void initializeBoard() {
        // Black pieces
        board[0][0] = new Piece("Black", "Rook");
        board[0][1] = new Piece("Black", "Knight");
        board[0][2] = new Piece("Black", "Bishop");
        board[0][3] = new Piece("Black", "Queen");
        board[0][4] = new Piece("Black", "King");
        board[0][5] = new Piece("Black", "Bishop");
        board[0][6] = new Piece("Black", "Knight");
        board[0][7] = new Piece("Black", "Rook");

        // Place Black pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("Black");
        }

        // White pieces
        board[7][0] = new Piece("White", "Rook");
        board[7][1] = new Piece("White", "Knight");
        board[7][2] = new Piece("White", "Bishop");
        board[7][3] = new Piece("White", "Queen");
        board[7][4] = new Piece("White", "King");
        board[7][5] = new Piece("White", "Bishop");
        board[7][6] = new Piece("White", "Knight");
        board[7][7] = new Piece("White", "Rook");

        // Place White pawns
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn("White");
        }
    }

    public void display() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j] + " "); // Calls the toString method of Piece
                } else {
                    System.out.print(". "); // Empty square
                }
            }
            System.out.println();
        }
    }

    public Piece getPiece(int row, int col) {
        return board[row][col]; // Get piece at the specified position
    }

    public void movePiece(int sourceRow, int sourceCol, int destRow, int destCol) {
        board[destRow][destCol] = board[sourceRow][sourceCol]; // Move piece
        board[sourceRow][sourceCol] = null; // Clear original position
    }
}
