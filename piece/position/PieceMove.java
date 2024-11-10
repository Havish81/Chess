package piece.position;

/**
 * The PieceMove class represents a move from one PiecePosition to another in a
 * game.
 */
public class PieceMove {

    private final PiecePosition from;
    private final PiecePosition to;

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
