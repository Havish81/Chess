package piece;

import piece.position.PiecePosition;
import piece.position.PiecePositionColumn;
import piece.position.PiecePositionRow;

/**
 * The Queen class extends ChessPiece and represents a queen chess piece with
 * a specified color.
 */
public class Queen extends ChessPiece {

    /**
     * The Queen piece constructor
     *
     * @param pieceColor The color of a piece.
     */
    public Queen(PieceColor pieceColor) {
        super(pieceColor);
        type = PieceType.Queen;
    }

    /**
     * The possibleMoves method calculates the legal moves for the Queen.
     * The Queen can move horizontally, vertically, and diagonally.
     * 
     * @param currentPosition The current position of the Queen on the board.
     * @return An array of PiecePosition objects representing legal moves.
     */
    @Override
    public PiecePosition[] possibleMoves(PiecePosition currentPosition) {
        PiecePosition[] moves = new PiecePosition[27]; // Maximum of 27 moves (7 in each direction)
        int moveIndex = 0;

        // Directions: horizontal, vertical, and diagonal
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}, // Vertical and horizontal
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1} // Diagonal directions
        };

        // Get the current row and column indices (0-7)
        int currentRowIndex = currentPosition.getRow().ordinal();
        int currentColumnIndex = currentPosition.getColumn().ordinal();

        // Loop through all possible directions
        for (int[] direction : directions) {
            int newRow = currentRowIndex;
            int newColumn = currentColumnIndex;

            // Move in the current direction as far as possible
            while (true) {
                newRow += direction[0];
                newColumn += direction[1];

                // If the new position is out of bounds, break the loop
                if (newRow < 0 || newRow >= 8 || newColumn < 0 || newColumn >= 8) {
                    break;
                }

                // Add valid move to the moves array
                moves[moveIndex++] = new PiecePosition(PiecePositionRow.values()[newRow], PiecePositionColumn.values()[newColumn]);
            }
        }

        // Return an array of valid moves, excluding null values
        PiecePosition[] validMoves = new PiecePosition[moveIndex];
        System.arraycopy(moves, 0, validMoves, 0, moveIndex);
        return validMoves;
    }
}
