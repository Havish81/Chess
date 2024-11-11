package piece.position;

/**
 * The PiecePosition class represents the position of a piece on a board using
 * row and column coordinates.
 */
public class PiecePosition {

    PiecePositionRow row;
    PiecePositionColumn column;

    public PiecePosition(PiecePositionRow row, PiecePositionColumn column) {
        this.row = row;
        this.column = column;
    }

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
