package chessboard;

import chesspiece.*;

/**
 * The `ChessBoard` class represents a chess board with pieces initialized in their starting positions.
 */
public abstract class ChessBoard {

    protected static final int BOARD_SIZE = 8;

    protected final ChessPiece[][] board;

    public ChessBoard() {
        board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        // Place Black pieces
        board[0][0] = new Rook(PieceColor.Black);
        board[0][1] = new Knight(PieceColor.Black);
        board[0][2] = new Bishop(PieceColor.Black);
        board[0][3] = new Queen(PieceColor.Black);
        board[0][4] = new King(PieceColor.Black);
        board[0][5] = new Bishop(PieceColor.Black);
        board[0][6] = new Knight(PieceColor.Black);
        board[0][7] = new Rook(PieceColor.Black);

        // Place Black pawns
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[1][i] = new Pawn(PieceColor.Black);
        }

        // Place White pieces
        board[7][0] = new Rook(PieceColor.White);
        board[7][1] = new Knight(PieceColor.White);
        board[7][2] = new Bishop(PieceColor.White);
        board[7][3] = new Queen(PieceColor.White);
        board[7][4] = new King(PieceColor.White);
        board[7][5] = new Bishop(PieceColor.White);
        board[7][6] = new Knight(PieceColor.White);
        board[7][7] = new Rook(PieceColor.White);

        // Place White pawns
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[6][i] = new Pawn(PieceColor.White);
        }
    }
}
