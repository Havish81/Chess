package piece;

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
}
