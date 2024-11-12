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

        boardPanel = new JPanel(new GridLayout(8, 8));
        frame.add(boardPanel);
        frame.setVisible(true);

        displayBoard();
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
            // Attempt to move the selected piece to the clicked square
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
}
