package piece;

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
}
