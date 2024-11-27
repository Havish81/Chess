package board;

import piece.*;

/**
 * The `ConsoleChessBoard` class extends `ChessBoard` and provides a method to
 * display the chess board in the console with pieces represented by characters.
 */
public class ConsoleChessBoard extends ChessBoard {

    /**
     * The constructor of the console version of the board.
     */
    public ConsoleChessBoard() {
        super();
    }

    /**
     * The `display` method prints out the chess board with pieces represented
     * by characters and empty cells with specific formatting.
     */
    @Override
    public void display() {
        // Display the header row
        System.out.println("\n  A  B  C  D  E  F  G  H");

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
        return switch (chessPiece.getType()) {
            case PieceType.Bishop -> 'B';
            case PieceType.King -> 'K';
            case PieceType.Knight -> 'N';
            case PieceType.Pawn -> 'P';
            case PieceType.Queen -> 'Q';
            case PieceType.Rook -> 'R';
            case PieceType.None -> '?';
            default -> '?';
        };
    }

    private String displayEmptyCell(int row, int column) {
        if ((row + column) % 2 == 0) {
            return "  ";
        } else {
            return "##";
        }
    }

}
