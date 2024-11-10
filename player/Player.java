package player;

import piece.PieceColor;

/**
 * The Player class represents a player in a game with a name and a piece color.
 */
public class Player {

    String name;
    PieceColor pieceColor;

    public Player(String name, PieceColor pieceColor) {
        this.name = name;
        this.pieceColor = pieceColor;
    }

    /**
     * The getName() method returns the name of the player.
     * 
     * @return The name of the player is being returned.
     */
    public String getName() {
        return name;
    }

    /**
     * The getColor() method returns the color of the pieces.
     * 
     * @return The color of the pieces.
     */
    public PieceColor getColor() {
        return pieceColor;
    }
}
