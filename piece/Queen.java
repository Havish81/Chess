package piece;

/**
 * The Queen class extends ChessPiece and represents a queen chess piece with a specified color.
 */
public class Queen extends ChessPiece {

    public Queen(PieceColor pieceColor) {
        super(pieceColor);

        type = PieceType.Queen;
    }
}
