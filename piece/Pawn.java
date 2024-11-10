package piece;

/**
 * The Pawn class extends ChessPiece and represents a pawn chess piece with a specified color.
 */
public class Pawn extends ChessPiece {

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);

        type = PieceType.Pawn;
    }
}
