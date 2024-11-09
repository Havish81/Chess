package chesspiece;

/**
 * The Knight class represents a knight chess piece with a specific color.
 */
public class Knight extends ChessPiece {

    public Knight(PieceColor pieceColor) {
        super(pieceColor);

        type = PieceType.Knight;
    }
}
