import board.GUIChessBoard;
import piece.ChessPiece;
import piece.PieceColor;
import piece.Rook;
import piece.Knight;
import piece.Bishop;
import piece.Queen;
import piece.King;
import piece.Pawn;

public class Chess {
    private ChessPiece[][] board;
    private GUIChessBoard guiBoard;

    public Chess() {
        board = new ChessPiece[8][8];
        guiBoard = new GUIChessBoard(this::getPiece, this::movePiece); // Pass methods for piece access and movement
        initializeBoard();
        guiBoard.displayBoard();
    }

    private void initializeBoard() {
        // Set up the board with pieces
        board[0][0] = new Rook(PieceColor.Black);
        board[0][1] = new Knight(PieceColor.Black);
        board[0][2] = new Bishop(PieceColor.Black);
        board[0][3] = new Queen(PieceColor.Black);
        board[0][4] = new King(PieceColor.Black);
        board[0][5] = new Bishop(PieceColor.Black);
        board[0][6] = new Knight(PieceColor.Black);
        board[0][7] = new Rook(PieceColor.Black);
        for (int i = 0; i < 8; i++) board[1][i] = new Pawn(PieceColor.Black);

        board[7][0] = new Rook(PieceColor.White);
        board[7][1] = new Knight(PieceColor.White);
        board[7][2] = new Bishop(PieceColor.White);
        board[7][3] = new Queen(PieceColor.White);
        board[7][4] = new King(PieceColor.White);
        board[7][5] = new Bishop(PieceColor.White);
        board[7][6] = new Knight(PieceColor.White);
        board[7][7] = new Rook(PieceColor.White);
        for (int i = 0; i < 8; i++) board[6][i] = new Pawn(PieceColor.White);
    }

    public ChessPiece getPiece(int row, int col) {
        return board[row][col];
    }

    public boolean movePiece(int[] move) {
        int startRow = move[0];
        int startCol = move[1];
        int endRow = move[2];
        int endCol = move[3];
        
        ChessPiece selectedPiece = board[startRow][startCol];
        if (selectedPiece != null && isValidMove(startRow, startCol, endRow, endCol)) {
            ChessPiece targetPiece = board[endRow][endCol];
            if (targetPiece != null && targetPiece.getColor() != selectedPiece.getColor()) {
                // Check if the captured piece is a King
                if (targetPiece instanceof King) {
                    String winner = selectedPiece.getColor() == PieceColor.White ? "White" : "Black";
                    guiBoard.showWinner(winner);  // Show winner pop-up
                    return true;  // Return to terminate further moves
                }
                System.out.println("Captured " + targetPiece);
            }
            // Move the selected piece to the new location
            board[endRow][endCol] = selectedPiece;
            board[startRow][startCol] = null;
            guiBoard.updateDisplay(); // Refresh the display after a move
            return true;
        }
        System.out.println("Invalid move.");
        return false;
    }

    private boolean isValidMove(int startRow, int startCol, int endRow, int endCol) {
        ChessPiece piece = board[startRow][startCol];
        if (piece == null) return false;
        // Basic move validation logic based on the piece type (simplified here)
        // Implement specific rules for each piece type if needed
        return true;
    }

    public static void main(String[] args) {
        new Chess();
    }
}
