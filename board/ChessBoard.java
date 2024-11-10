package board;

import piece.*;
import piece.position.PieceMove;
import piece.position.PiecePosition;
import player.Player;

/**
 * The `ChessBoard` class represents a chess board with pieces initialized in
 * their starting positions.
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

    /**
     * The function isCheck returns true to indicate whether a player is in
     * check in a game of chess.
     *
     * @param player The parameter represents the player for which you want to
     * check if they are in a check position.
     * @return The method is returning a boolean value.
     */
    public boolean isCheck(Player player) {
        return false;
    }

    /**
     * The function isCheckmate returns true to indicate whether a player is in
     * checkmate in a game of chess.
     *
     * @param player The parameter represents the player for which you want to
     * check if they are in a checkmate position.
     * @return The method is returning a boolean value.
     */
    public boolean isCheckmate(Player player) {
        return false;
    }

    /**
     * The method moves a piece from one position on the board to another
     * position.
     *
     * @param pieceMove PieceMove object containing information about the piece
     * being moved and its estination.
     */
    public void movePiece(PieceMove pieceMove) {
        PiecePosition from = pieceMove.getFromPosition();
        PiecePosition to = pieceMove.getToPosition();
        board[to.getRow().ordinal()][to.getColumn().ordinal()] = board[from.getRow().ordinal()][from.getColumn().ordinal()];
        board[from.getRow().ordinal()][from.getColumn().ordinal()] = null;
    }
}
