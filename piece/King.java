package piece;

/**
 * The King class extends ChessPiece and represents a king piece in chess with a specified color.
 */
public class King extends ChessPiece {

    public King(PieceColor pieceColor) {
        super(pieceColor);

        type = PieceType.King;
    }
}
