package game;

import board.ChessBoard;
import piece.PieceColor;
import player.Player;

/**
 * The `Game` class represents a chess game with functionality for player moves,
 * check, and checkmate detection.
 */
public abstract class Game {

    protected ChessBoard board;

    protected Player whitePlayer;
    protected Player blackPlayer;
    protected Player currentPlayer;

    /**
     * The Game constructor initializes a chess game and sets the current player to white.
     */
    public Game() {
        whitePlayer = new Player("White", PieceColor.White);
        blackPlayer = new Player("Black", PieceColor.Black);
        currentPlayer = whitePlayer;
    }
}
