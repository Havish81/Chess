package piece.position;

/**
 * The PiecePosition class represents the position of a piece on a board using row and column
 * coordinates.
 */
public class PiecePosition {

    PiecePositionRow row;
    PiecePositionColumn column;

    public PiecePosition(PiecePositionRow row, PiecePositionColumn column) {
        this.row = row;
        this.column = column;
    }

    /**
     * The function `getRow()` returns the `PiecePositionRow` object representing the row of a piece
     * position.
     * 
     * @return The method `getRow()` is returning an object of type `PiecePositionRow`.
     */
    public PiecePositionRow getRow() {
        return row;
    }

    /**
     * The function `getColumn()` returns the column of a piece position.
     * 
     * @return The method `getColumn()` is returning an object of type `PiecePositionColumn`.
     */
    public PiecePositionColumn getColumn() {
        return column;
    }
}
