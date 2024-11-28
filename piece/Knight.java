package piece;

import java.util.ArrayList;
import piece.position.PiecePosition;
import piece.position.PiecePositionColumn;
import piece.position.PiecePositionRow;

/**
 * The Knight class represents a knight chess piece with a specific color.
 */
public class Knight extends ChessPiece {

    /**
     * The Knight piece constructor
     *
     * @param pieceColor The color of a piece.
     */
    public Knight(PieceColor pieceColor) {
        super(pieceColor);
        type = PieceType.Knight;
    }

    /**
     * The possibleMoves method calculates the legal moves for the knight.
     * 
     * @param currentPosition The current position of the knight on the board.
     * @return An array of PiecePosition objects representing legal moves.
     */
    @Override
    public PiecePosition[] possibleMoves(PiecePosition currentPosition) {
        ArrayList<PiecePosition> moves = new ArrayList<>();
        
        // Knight's L-shaped moves (8 possible directions)
        int[][] directions = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, 
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        // Get the current row and column indices (0-7)
        int currentRowIndex = currentPosition.getRow().ordinal(); 
        int currentColumnIndex = currentPosition.getColumn().ordinal();

        // Loop through all possible L-shaped moves
        for (int[] direction : directions) {
            int newRow = currentRowIndex + direction[0];
            int newColumn = currentColumnIndex + direction[1];

            // Check if the new position is within bounds
            if (isInBounds(newRow, newColumn)) {
                // Convert back to PiecePosition using enums for the new position
                PiecePosition newPosition = new PiecePosition(PiecePositionRow.values()[newRow], PiecePositionColumn.values()[newColumn]);
                moves.add(newPosition);  // Add valid move to list
            }
        }

        // Convert the list to an array and return it
        return moves.toArray(new PiecePosition[0]);
    }

    /**
     * Checks if the given position is within the bounds of the 8x8 chessboard.
     *
     * @param row The row index.
     * @param column The column index.
     * @return true if the position is within bounds, false otherwise.
     */
    private boolean isInBounds(int row, int column) {
        return row >= 0 && row < 8 && column >= 0 && column < 8;
    }
}
