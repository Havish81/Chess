package chessboard;

import chesspiece.*;

/**
 * The `ConsoleChessBoard` class extends `ChessBoard` and provides a method to display the chess board
 * in the console with pieces represented by characters.
 */
public class ConsoleChessBoard extends ChessBoard {

    public ConsoleChessBoard() {
        super();
    }

    /**
     * The `display` function prints out the chess board with pieces represented by characters and
     * empty cells with specific formatting.
     */
    public void display() {
        // Display the header row
        System.out.println("  A  B  C  D  E  F  G  H");

        // Display pieces
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.printf("%d ", BOARD_SIZE - i);

            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] != null) {
                    System.out.printf("%c%c ", displayPieceColor(board[i][j]), displayPieceType(board[i][j]));
                } else {
                    System.out.print(displayEmptyCell(i, j) + ' ');
                }
            }
            System.out.println();
        }
    }

    private char displayPieceColor(ChessPiece chessPiece) {
        if (chessPiece.getColor() == PieceColor.Black) {
            return 'b';
        } else {
            return 'w';
        }
    }

    private char displayPieceType(ChessPiece chessPiece) {
        switch (chessPiece.getType()) {
            case PieceType.Bishop:
                return 'B';
            case PieceType.King:
                return 'K';
            case PieceType.Knight:
                return 'N';
            case PieceType.Pawn:
                return 'P';
            case PieceType.Queen:
                return 'Q';
            case PieceType.Rook:
                return 'R';
            case PieceType.None:
                return '?';
            default:
                return '?';
        }
    }

    private String displayEmptyCell(int row, int column) {
        if ((row + column) % 2 == 0) {
            return "  ";
        } else {
            return "##";
        }
    }

}
