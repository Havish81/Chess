package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import piece.ChessPiece;
import piece.PieceColor;
import java.util.function.BiFunction;
import java.util.function.Function;

public class GUIChessBoard extends JPanel {
    private final BiFunction<Integer, Integer, ChessPiece> pieceProvider;
    private final Function<int[], Boolean> moveExecutor;
    private JFrame frame;
    private JPanel boardPanel;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public GUIChessBoard(BiFunction<Integer, Integer, ChessPiece> pieceProvider, Function<int[], Boolean> moveExecutor) {
        this.pieceProvider = pieceProvider;
        this.moveExecutor = moveExecutor;
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setResizable(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createColumnLabels(), BorderLayout.NORTH);
        mainPanel.add(createRowLabelsAndBoard(), BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);

        displayBoard();
    }

    private JPanel createColumnLabels() {
        JPanel colLabels = new JPanel(new GridLayout(1, 8));
        String[] columns = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (String col : columns) {
            JLabel label = new JLabel(col, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            colLabels.add(label);
        }
        return colLabels;
    }

    private JPanel createRowLabelsAndBoard() {
        JPanel boardWithRowLabels = new JPanel(new BorderLayout());
        boardWithRowLabels.add(createRowLabels(), BorderLayout.WEST);

        boardPanel = new JPanel(new GridLayout(8, 8));
        boardPanel.setPreferredSize(new Dimension(600, 600));
        boardWithRowLabels.add(boardPanel, BorderLayout.CENTER);

        return boardWithRowLabels;
    }

    private JPanel createRowLabels() {
        JPanel rowLabels = new JPanel(new GridLayout(8, 1));
        for (int i = 8; i > 0; i--) {
            JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            rowLabels.add(label);
        }
        return rowLabels;
    }

    public void displayBoard() {
        boardPanel.removeAll();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton square = new JButton();
                square.setFont(new Font("Arial", Font.PLAIN, 24));
                square.setHorizontalAlignment(SwingConstants.CENTER);

                ChessPiece piece = pieceProvider.apply(row, col);
                if (piece != null) {
                    square.setText(getPieceLabel(piece));
                }

                square.setBackground((row + col) % 2 == 0 ? Color.LIGHT_GRAY : Color.DARK_GRAY);

                final int r = row;
                final int c = col;
                square.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleSquareClick(r, c);
                    }
                });

                boardPanel.add(square);
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private void handleSquareClick(int row, int col) {
        if (selectedRow == -1 && selectedCol == -1) {
            if (pieceProvider.apply(row, col) != null) {
                selectedRow = row;
                selectedCol = col;
            }
        } else {
            boolean moved = moveExecutor.apply(new int[]{selectedRow, selectedCol, row, col});
            if (moved) {
                System.out.println("Moved piece from (" + selectedRow + ", " + selectedCol + ") to (" + row + ", " + col + ")");
            }
            selectedRow = -1;
            selectedCol = -1;
            displayBoard();
        }
    }

    private String getPieceLabel(ChessPiece piece) {
        char color = (piece.getColor() == PieceColor.Black) ? 'b' : 'w';
        char type;
        switch (piece.getType()) {
            case Rook: type = 'R'; break;
            case Knight: type = 'N'; break;
            case Bishop: type = 'B'; break;
            case Queen: type = 'Q'; break;
            case King: type = 'K'; break;
            case Pawn: type = 'P'; break;
            default: type = '?';
        }
        return color + "" + type;
    }

    public void updateDisplay() {
        displayBoard();
    }

    /**
     * Show the winner in a pop-up window and terminate the game.
     */
    public void showWinner(String winner) {
        JOptionPane.showMessageDialog(frame, winner + " wins the game!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);  // Terminate the game after showing the message
    }
}
