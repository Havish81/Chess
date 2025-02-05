package piece;

import piece.position.PiecePosition;
import piece.position.PiecePositionColumn;
import piece.position.PiecePositionRow;

/**
 * The Bishop class extends ChessPiece and represents a bishop chess piece with
 * a specified color.
 */
public class Bishop extends ChessPiece {

    /**
     * The Bishop piece constructor
     *
     * @param pieceColor The color of a piece.
     */
    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
        type = PieceType.Bishop;
    }

    /**
     * The possibleMoves method calculates the legal moves for the Bishop.
     * 
     * @param currentPosition The current position of the Bishop on the board.
     * @return An array of PiecePosition objects representing legal moves.
     */
    @Override
    public PiecePosition[] possibleMoves(PiecePosition currentPosition) {
        // Initialize an array with a maximum of 13 possible moves (bishop moves diagonally)
        PiecePosition[] moves = new PiecePosition[13];
        int moveCount = 0;

        // Bishop moves diagonally in 4 possible directions
        int[][] directions = {
            {1, 1}, {-1, 1}, {1, -1}, {-1, -1} // Diagonal directions
        };

        // Get the current row and column indices (0-7)
        int currentRowIndex = currentPosition.getRow().ordinal();
        int currentColumnIndex = currentPosition.getColumn().ordinal();

        // Loop through all possible diagonal directions
        for (int[] direction : directions) {
            int newRow = currentRowIndex;
            int newColumn = currentColumnIndex;

            // Move in the current diagonal direction as far as possible 
            while (true) {
                newRow += direction[0];
                newColumn += direction[1];

                // If the new position is out of bounds, break the loop
                if (newRow < 0 || newRow >= 8 || newColumn < 0 || newColumn >= 8) {
                    break;
                }

                // Convert back to PiecePosition using enums for the new position
                PiecePosition newPosition = new PiecePosition(PiecePositionRow.values()[newRow], PiecePositionColumn.values()[newColumn]);

                // Add the valid move to the array
                if (moveCount < moves.length) {
                    moves[moveCount++] = newPosition;
                }
            }
        }

        // Return a new array with only the valid moves
        PiecePosition[] validMoves = new PiecePosition[moveCount];
        System.arraycopy(moves, 0, validMoves, 0, moveCount);
        return validMoves;
    }
}
