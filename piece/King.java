package piece;

/**
 * The King class extends ChessPiece and represents a king piece in chess with a
 * specified color.
 */
public class King extends ChessPiece {

    /**
     * The King piece constructor
     *
     * @param pieceColor The color of a piece.
     */
    public King(PieceColor pieceColor) {
        super(pieceColor);

        type = PieceType.King;
    }
}
