package piece;

import java.util.ArrayList;
import piece.position.PiecePosition;
import piece.position.PiecePositionColumn;
import piece.position.PiecePositionRow;

/**
 * The Rook class extends ChessPiece and represents a rook chess piece with
 * a specified color.
 */
public class Rook extends ChessPiece {

    /**
     * The Rook piece constructor
     *
     * @param pieceColor The color of a piece.
     */
    public Rook(PieceColor pieceColor) {
        super(pieceColor);
        type = PieceType.Rook;
    }

    /**
     * The possibleMoves method calculates the legal moves for the Rook.
     * 
     * @param currentPosition The current position of the Rook on the board.
     * @return An array of PiecePosition objects representing legal moves.
     */
    @Override
    public PiecePosition[] possibleMoves(PiecePosition currentPosition) {
        ArrayList<PiecePosition> moves = new ArrayList<>();

        // Rook moves along the rows and columns in 4 possible directions
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1} // Vertical and horizontal directions
        };

        // Get the current row and column indices (0-7)
        int currentRowIndex = currentPosition.getRow().ordinal();
        int currentColumnIndex = currentPosition.getColumn().ordinal();

        // Loop through all possible row/column directions
        for (int[] direction : directions) {
            int newRow = currentRowIndex;
            int newColumn = currentColumnIndex;

            // Move in the current row/column direction as far as possible (no boundaries)
            while (true) {
                newRow += direction[0];
                newColumn += direction[1];

                // If the new position is out of bounds, break the loop
                if (newRow < 0 || newRow >= 8 || newColumn < 0 || newColumn >= 8) {
                    break;
                }

                // Convert back to PiecePosition using enums for the new position
                PiecePosition newPosition = new PiecePosition(PiecePositionRow.values()[newRow], PiecePositionColumn.values()[newColumn]);
                moves.add(newPosition);  // Add valid move to list
            }
        }

        // Convert the list to an array and return it
        return moves.toArray(new PiecePosition[0]);
    }
}
