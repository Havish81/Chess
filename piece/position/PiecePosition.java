package piece.position;

/**
 * The PiecePosition class represents the position of a piece on a board using
 * row and column coordinates.
 */
public class PiecePosition {

    PiecePositionRow row;
    PiecePositionColumn column;

    /**
     * The constructor of PiecePosition sets the row and column of a piece on a
     * board.
     *
     * @param row The `PiecePositionRow` parameter represents the row position
     * of a piece on a game board. It is used to specify the row where a piece
     * is located.
     * @param column The `column` parameter in the `PiecePosition` method
     * represents the column position of a piece on a game board. It is used to
     * specify the column position of the piece within a row.
     */
    public PiecePosition(PiecePositionRow row, PiecePositionColumn column) {
        this.row = row;
        this.column = column;
    }

    /**
     * The constructor of `PiecePosition` maps input row and column values to
     * corresponding enums `PiecePositionRow` and `PiecePositionColumn`.
     *
     * @param row The `row` parameter in the `PiecePosition` method represents
     * the row position of a chess piece on a chessboard. The method maps the
     * numeric row input to an enum type `PiecePositionRow` which represents the
     * corresponding row on the chessboard.
     * @param column The `column` parameter in the `PiecePosition` method
     * represents the column position of a piece on a chessboard. It is an
     * integer value ranging from 0 to 7, where 0 corresponds to column A, 1
     * corresponds to column B, and so on up to 7.
     */
    public PiecePosition(int row, int column) {
        switch (row) {
            case 0:
                this.row = PiecePositionRow.Row1;
                break;
            case 1:
                this.row = PiecePositionRow.Row2;
                break;
            case 2:
                this.row = PiecePositionRow.Row3;
                break;
            case 3:
                this.row = PiecePositionRow.Row4;
                break;
            case 4:
                this.row = PiecePositionRow.Row5;
                break;
            case 5:
                this.row = PiecePositionRow.Row6;
                break;
            case 6:
                this.row = PiecePositionRow.Row7;
                break;
            case 7:
                this.row = PiecePositionRow.Row8;
                break;
            default:
                this.row = PiecePositionRow.Row1;
        }

        switch (column) {
            case 0:
                this.column = PiecePositionColumn.A;
                break;
            case 1:
                this.column = PiecePositionColumn.B;
                break;
            case 2:
                this.column = PiecePositionColumn.C;
                break;
            case 3:
                this.column = PiecePositionColumn.D;
                break;
            case 4:
                this.column = PiecePositionColumn.E;
                break;
            case 5:
                this.column = PiecePositionColumn.F;
                break;
            case 6:
                this.column = PiecePositionColumn.G;
                break;
            case 7:
                this.column = PiecePositionColumn.H;
                break;
            default:
                this.column = PiecePositionColumn.A;
        }
    }

    /**
     * The function `getRow()` returns the `PiecePositionRow` object
     * representing the row of a piece position.
     *
     * @return The method `getRow()` is returning an object of type
     * `PiecePositionRow`.
     */
    public PiecePositionRow getRow() {
        return row;
    }

    /**
     * The function `getColumn()` returns the column of a piece position.
     *
     * @return The method `getColumn()` is returning an object of type
     * `PiecePositionColumn`.
     */
    public PiecePositionColumn getColumn() {
        return column;
    }
}
