package player;

import piece.PieceColor;

/**
 * The Player class represents a player in a game with a name and a piece color.
 */
public class Player {

    String name;
    PieceColor pieceColor;

    /**
     * The constructir initializes a Player object with a given name and piece
     * color.
     *
     * @param name The "name" parameter in the code represents the name of the
     * player. It is used to store and identify the name of the player who is
     * associated with the instance of the Player class.
     * @param pieceColor The `pieceColor` parameter in the `Player` constructor
     * represents the color of the game piece associated with the player. It is
     * used to assign a specific color to the player's game piece, such as black
     * or white in a chess game.
     */
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
