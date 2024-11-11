package piece.position;

/**
 * The PieceMove class represents a move from one PiecePosition to another in a
 * game.
 */
public class PieceMove {

    private final PiecePosition from;
    private final PiecePosition to;

    /**
     * The PieceMove constructor assigns the from and to positions for a piece
     * movement.
     *
     * @param from The `from` parameter in the `PieceMove` method represents the
     * current position of the piece that you want to move. It contains
     * information such as the row and column of the piece on the game board
     * before the move.
     * @param to The `to` parameter in the `PieceMove` method represents the
     * position where the piece is being moved to. It specifies the destination
     * position for the piece.
     */
    public PieceMove(PiecePosition from, PiecePosition to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the `PiecePosition` object representing the starting position of
     * a piece.
     *
     * @return Returns the `PiecePosition` object representing the starting
     * position of a piece.
     */
    public PiecePosition getFromPosition() {
        return from;
    }

    /**
     * Returns the `PiecePosition` object representing the ending position of a
     * piece.
     *
     * @return Returns the `PiecePosition` object representing the ending
     * position of a piece.
     */
    public PiecePosition getToPosition() {
        return to;
    }
}
