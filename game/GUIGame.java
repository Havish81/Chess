package game;

import board.GUIChessBoard;

/**
 * The `Game` class represents a chess game with functionality for player moves,
 * check, and checkmate detection.
 */
public class GUIGame extends Game {

    /**
     * The Game constructor initializes a chess game and sets the current player
     * to white.
     */
    public GUIGame() {
        super();

        board = new GUIChessBoard();
    }

    /**
     * The `start` method implements a turn-based chess game where players can
     * make moves, check for check and checkmate, and switch turns until the
     * game ends.
     */
    @Override
    public void start() {
        board.display();
    }
}
