package piece;

/**
 * The Bishop class extends ChessPiece and represents a bishop chess piece with a specified color.
 */
public class Bishop extends ChessPiece {

    public Bishop(PieceColor pieceColor) {
        super(pieceColor);

        type = PieceType.Bishop;
    }
}
