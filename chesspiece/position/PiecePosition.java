package chesspiece.position;

public class PiecePosition {

    PiecePositionRow row;
    PiecePositionColumn column;

    public PiecePosition(PiecePositionRow row, PiecePositionColumn column) {
        this.row = row;
        this.column = column;
    }

    public PiecePositionRow getRow() {
        return row;
    }

    public PiecePositionColumn getColumn() {
        return column;
    }
}
