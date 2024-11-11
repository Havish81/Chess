package board;

import java.awt.Color;
import javax.swing.*;

public class GUIChessBoard extends ChessBoard {

    private final int WINDOW_WIDTH = 900;
    private final int WINDOW_HEIGHT = 900;
    private JFrame frame;

    /**
     * The constructor of the GUI version of the board.
     */
    public GUIChessBoard() {
        super();

        initGUI();
    }

    private void initGUI() {
        frame = new JFrame("Chess");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        createBoard();

        // Make the window visible
        frame.setVisible(true);
    }

    private void createBoard() {
        createColumnLabels();
        createRowLabels();
    }

    private void createColumnLabels() {
        for (int i = 1; i <= BOARD_SIZE + 1; i++) {
            JLabel label = new JLabel(String.format("%c", (char) ('A' + i - 1)));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setBounds(i * WINDOW_WIDTH / 9, 0, WINDOW_WIDTH / 9, WINDOW_HEIGHT / 9);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            frame.getContentPane().add(label);
        }
    }

    private void createRowLabels() {
        for (int i = 1; i <= BOARD_SIZE + 1; i++) {
            JLabel label = new JLabel(String.format("%d", BOARD_SIZE - i + 1));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setBounds(0, i * WINDOW_WIDTH / 9, WINDOW_WIDTH / 9, WINDOW_HEIGHT / 9);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            frame.getContentPane().add(label);
        }
    }

    /**
     * The `display` method prints out the chess board with pieces represented
     * by characters and empty cells with specific formatting.
     */
    @Override
    public void display() {

    }
}
