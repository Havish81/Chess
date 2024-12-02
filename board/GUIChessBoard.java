package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import piece.ChessPiece;
import piece.Knight;
import piece.Pawn;
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
    @SuppressWarnings("Convert2Lambda")
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
            ChessPiece selectedPiece = pieceProvider.apply(selectedRow, selectedCol);

                if (selectedPiece != null && selectedPiece.getColor() == currentTurn) {
                    ChessPiece targetPiece = pieceProvider.apply(row, col);

                    // Ensure the selected piece can capture or move
                    if (targetPiece == null || selectedPiece.canCapture(targetPiece)) {
                        PiecePosition currentPosition = new PiecePosition(
                            PiecePositionRow.values()[selectedRow],
                            PiecePositionColumn.values()[selectedCol]
                        );

                        // Get the legal moves for the selected piece
                        PiecePosition[] legalMoves = selectedPiece.possibleMoves(currentPosition);

                        // Check if the destination (row, col) is a legal move
                        boolean isValidMove = false;
                        for (PiecePosition legalMove : legalMoves) {

                            if (legalMove.getRow() == PiecePositionRow.values()[row] &&
                                legalMove.getColumn() == PiecePositionColumn.values()[col]) {
                                isValidMove = true;
                                break;
                                }
                        }

                        // Check for path-blocking, unless the piece is a knight or it's a pawn's diagonal capture
                        if (isValidMove && !(selectedPiece instanceof Knight)) {
                            boolean pathBlocked = isPathBlocked(new PiecePosition(PiecePositionRow.values()[selectedRow], PiecePositionColumn.values()[selectedCol]),
                                    new PiecePosition(PiecePositionRow.values()[row], PiecePositionColumn.values()[col]), pieceProvider);

                            // If pawn, check if the move is a diagonal capture
                           if (selectedPiece instanceof Pawn && Math.abs(selectedRow - row) == 1 && Math.abs(selectedCol - col) == 1) {
                               // Diagonal capture
                                pathBlocked = false;
                            }

                            if (pathBlocked) {
                                isValidMove = false;  // Block move if path is blocked
                            }
                        }

                        if (isValidMove) {
                            boolean moved = moveExecutor.apply(new int[]{selectedRow, selectedCol, row, col});
                            if (moved) {
                                System.out.println("Moved piece from (" + selectedRow + ", " + selectedCol + ") to (" + row + ", " + col + ")");
                                switchTurn();  // After a valid move, switch turn
                            }
                        } else {
                            System.out.println("Invalid move for " + selectedPiece.getType());
                        }
                    } else {
                        System.out.println("Cannot move to (" + row + ", " + col + ") - invalid capture");
                    }
                }
                selectedRow = -1;
                selectedCol = -1;
                displayBoard();
        }
    }

    // Check if the path between the starting and target positions is blocked by a piece
    private boolean isPathBlocked(PiecePosition currentPosition, PiecePosition targetPosition, BiFunction<Integer, Integer, ChessPiece> pieceProvider) {
        int startRow = currentPosition.getRow().ordinal();
        int startColumn = currentPosition.getColumn().ordinal();
        int endRow = targetPosition.getRow().ordinal();
        int endColumn = targetPosition.getColumn().ordinal();

        // Check if the move is horizontal, vertical, or diagonal
        if (startRow == endRow) {  // Horizontal move
            int step = (endColumn > startColumn) ? 1 : -1;
            for (int col = startColumn + step; col != endColumn; col += step) {
                ChessPiece piece = pieceProvider.apply(startRow, col);
                if (piece != null) {
                    return true; // Path is blocked
                }
            }
        } else if (startColumn == endColumn) {  // Vertical move
            int step = (endRow > startRow) ? 1 : -1;
            for (int row = startRow + step; row != endRow; row += step) {
                ChessPiece piece = pieceProvider.apply(row, startColumn);
                if (piece != null) {
                    return true; // Path is blocked
                }
            }
        } else if (Math.abs(startRow - endRow) == Math.abs(startColumn - endColumn)) {  // Diagonal move
            int rowStep = (endRow > startRow) ? 1 : -1;
            int colStep = (endColumn > startColumn) ? 1 : -1;
            int row = startRow + rowStep;
            int col = startColumn + colStep;

            while (row != endRow && col != endColumn) {
                ChessPiece piece = pieceProvider.apply(row, col);
                if (piece != null) {
                    return true; // Path is blocked
                }
                row += rowStep;
                col += colStep;
            }
        }

        return false; // Path is not blocked
    }

    // Switch turns between White and Black
    private void switchTurn() {
        currentTurn = (currentTurn == PieceColor.White) ? PieceColor.Black : PieceColor.White;
        frame.setTitle(currentTurn == PieceColor.White ? "White's Turn" : "Black's Turn");
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
