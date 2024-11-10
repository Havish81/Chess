package piece;

/**
 * The Rook class extends ChessPiece and represents a rook piece in chess with a specified color.
 */
public class Rook extends ChessPiece {

    public Rook(PieceColor pieceColor) {
        super(pieceColor);

        type = PieceType.Rook;
    }
}
