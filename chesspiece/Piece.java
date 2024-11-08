package chesspiece;

import chesspiece.position.PiecePosition;

public abstract class Piece {

    private PieceColor color = PieceColor.Black;
    protected PieceType type = PieceType.None;

    public Piece(PieceColor pieceColor) {
        this.color = pieceColor;
    }

    public PiecePosition[] possibleMoves(PiecePosition currentPosition) {
        return null;
    }

    public PieceColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }
}
