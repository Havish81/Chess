package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import piece.ChessPiece;
import piece.PieceColor;
import piece.position.PiecePosition;
import piece.position.PiecePositionColumn;
import piece.position.PiecePositionRow;

import java.util.function.BiFunction;
import java.util.function.Function;

public class GUIChessBoard extends JPanel {
    private final BiFunction<Integer, Integer, ChessPiece> pieceProvider;
    private final Function<int[], Boolean> moveExecutor;
    private JFrame frame;
    private JPanel boardPanel;
    private int selectedRow = -1;
    private int selectedCol = -1;

    private PieceColor currentTurn = PieceColor.White;  // Track the current player's turn

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

    // Display the board
    public void displayBoard() {
        boardPanel.removeAll();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton square = new JButton();
                square.setFont(new Font("Arial", Font.PLAIN, 24));
                square.setHorizontalAlignment(SwingConstants.CENTER);

                ChessPiece piece = pieceProvider.apply(row, col);
                if (piece != null) {
                    String imagePath = piece.getImagePath();
                    ImageIcon pieceImage = new ImageIcon(imagePath);
                    square.setIcon(pieceImage);
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
        ChessPiece piece = pieceProvider.apply(row, col);
        if (piece != null && piece.getColor() == currentTurn) {
            selectedRow = row;
            selectedCol = col;
        }
    } else {
        ChessPiece piece = pieceProvider.apply(selectedRow, selectedCol);
        if (piece != null && piece.getColor() == currentTurn) {
            // Get the legal moves for the selected piece
            PiecePosition currentPosition = new PiecePosition(PiecePositionRow.values()[selectedRow], PiecePositionColumn.values()[selectedCol]);
            PiecePosition[] legalMoves = piece.possibleMoves(currentPosition);
            
            // Check if the destination (row, col) is a legal move
            boolean isValidMove = false;
            for (PiecePosition legalMove : legalMoves) {
                if (legalMove.getRow() == PiecePositionRow.values()[row] && legalMove.getColumn() == PiecePositionColumn.values()[col]) {
                    isValidMove = true;
                    break;
                }
            }
            
            if (isValidMove) {
                boolean moved = moveExecutor.apply(new int[]{selectedRow, selectedCol, row, col});
                if (moved) {
                    System.out.println("Moved piece from (" + selectedRow + ", " + selectedCol + ") to (" + row + ", " + col + ")");
                    switchTurn();  // After a valid move, switch turn
                }
            } else {
                System.out.println("Invalid move for " + piece.getType());
            }
        }
        selectedRow = -1;
        selectedCol = -1;
        displayBoard();
    }
}


    // Switch turns between White and Black
    private void switchTurn() {
        currentTurn = (currentTurn == PieceColor.White) ? PieceColor.Black : PieceColor.White;
    }

    // Update board display (to be used after every move)
    public void updateDisplay() {
        displayBoard();
    }

    // Show winner and end the game
    public void showWinner(String winner) {
        JOptionPane.showMessageDialog(frame, winner + " wins the game!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);  // Terminate the game after showing the message
    }
}
