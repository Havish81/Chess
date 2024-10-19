public class Piece {
    private final String color;
    private final String type;

    public Piece(String color, String type) {
        this.color = color;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.charAt(0) + ""; // Returns the first letter of the type (e.g., 'R' for Rook)
    }
}
