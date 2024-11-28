package piece;

import java.util.ArrayList;
import piece.position.PiecePosition;
import piece.position.PiecePositionColumn;
import piece.position.PiecePositionRow;

/**
 * The Pawn class extends ChessPiece and represents a pawn chess piece with a
 * specified color.
 */
public class Pawn extends ChessPiece {

    /**
     * The Pawn piece constructor
     *
     * @param pieceColor The color of a piece.
     */
    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
        type = PieceType.Pawn;
    }

    /**
     * The possibleMoves method calculates the legal forward moves for the pawn.
     * - Pawn can move forward one square.
     * - On the first move, it can move forward up to two squares.
     * 
     * @param currentPosition The current position of the Pawn on the board.
     * @return An array of PiecePosition objects representing legal moves.
     */
    @Override
    public PiecePosition[] possibleMoves(PiecePosition currentPosition) {
        ArrayList<PiecePosition> moves = new ArrayList<>();

        // Get the current row and column indices (0-7)
        int currentRowIndex = currentPosition.getRow().ordinal();
        int currentColumnIndex = currentPosition.getColumn().ordinal();

        // Determine the direction based on color
        int direction = (getColor() == PieceColor.White) ? -1 : 1; // White pawns move UP (-1), Black pawns move DOWN (+1)

        // Move one square forward
        int newRow = currentRowIndex + direction;
        if (newRow >= 0 && newRow < 8) {
            moves.add(new PiecePosition(PiecePositionRow.values()[newRow], PiecePositionColumn.values()[currentColumnIndex]));
        }

        // Move two squares forward (only on the pawn's first move)
        boolean isFirstMove = (getColor() == PieceColor.White && currentRowIndex == 6) || 
                              (getColor() == PieceColor.Black && currentRowIndex == 1);
        if (isFirstMove) {
            newRow = currentRowIndex + 2 * direction;
            if (newRow >= 0 && newRow < 8) {
                moves.add(new PiecePosition(PiecePositionRow.values()[newRow], PiecePositionColumn.values()[currentColumnIndex]));
            }
        }

        // Convert the list to an array and return it
        return moves.toArray(new PiecePosition[0]);
    }
}
