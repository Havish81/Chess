package piece;

import piece.position.PiecePosition;

/**
 * The abstract class `ChessPiece` represents a chess piece with color and type properties, providing
 * methods to calculate possible moves and retrieve color and type information.
 */
public abstract class ChessPiece {

    private PieceColor color = PieceColor.Black;
    protected PieceType type = PieceType.None;

    public ChessPiece(PieceColor pieceColor) {
        this.color = pieceColor;
    }

    /**
     * The function possibleMoves returns an array of PiecePosition objects representing the possible
     * moves from a given currentPosition.
     * 
     * @param currentPosition The `currentPosition` parameter represents the current position of a
     * piece on a game board. The method `possibleMoves` is expected to return an array of
     * `PiecePosition` objects representing the possible moves that the piece can make from its current
     * position.
     * @return The method returns an array of possible moves.
     */
    public PiecePosition[] possibleMoves(PiecePosition currentPosition) {
        return null;
    }

    /**
     * The getColor() function returns the color of a piece.
     * 
     * @return The method `getColor()` is returning the color of the piece.
     */
    public PieceColor getColor() {
        return color;
    }

    /**
     * The function `getType()` returns the type of a piece.
     * 
     * @return The method `getType()` is returning the type of the piece.
     */
    public PieceType getType() {
        return type;
    }
}
