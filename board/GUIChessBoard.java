package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import piece.ChessPiece;
import piece.PieceColor;

public class GUIChessBoard extends ChessBoard {

    private JFrame frame;
    private JPanel boardPanel;
    private ChessPiece selectedPiece = null; // Store selected piece
    private int selectedRow = -1;
    private int selectedCol = -1;

    public GUIChessBoard() {
        super(); // Initializes the board with pieces
        initializeGUI();
        displayBoard();
    }

    private void initializeGUI() {
        frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setResizable(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createColumnLabels(), BorderLayout.NORTH);
        mainPanel.add(createRowLabelsAndBoard(), BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
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
        boardPanel.removeAll(); // Clear existing components

        // Loop through the board and add each piece or empty square
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                JButton square = new JButton();
                square.setFont(new Font("Arial", Font.PLAIN, 24));
                square.setHorizontalAlignment(SwingConstants.CENTER);

                ChessPiece piece = board[i][j];
                if (piece != null) {
                    String pieceLabel = getPieceLabel(piece);
                    square.setText(pieceLabel);
                }

                // Set background color for the squares
                if ((i + j) % 2 == 0) {
                    square.setBackground(Color.LIGHT_GRAY);
                } else {
                    square.setBackground(Color.DARK_GRAY);
                }

                // Add ActionListener to handle clicks
                final int row = i;
                final int col = j;
                square.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleSquareClick(row, col);
                    }
                });

                boardPanel.add(square);
            }
        }

        frame.pack();
    }

    private void handleSquareClick(int row, int col) {
        ChessPiece piece = board[row][col];

        if (selectedPiece == null) { // First click to select a piece
            if (piece != null) { // Ensure there's a piece to select
                selectedPiece = piece;
                selectedRow = row;
                selectedCol = col;
                System.out.println("Selected piece at (" + row + ", " + col + ")");
            }
        } else { // Second click to move the piece
            List<int[]> validMoves = getValidMoves(selectedPiece, selectedRow, selectedCol);

            // Check if the selected destination is in the valid moves list
            boolean isValidMove = validMoves.stream().anyMatch(move -> move[0] == row && move[1] == col);

            if (isValidMove) {
                // Move the selected piece to the new square
                board[row][col] = selectedPiece;
                board[selectedRow][selectedCol] = null;
                System.out.println("Moved piece to (" + row + ", " + col + ")");
            } else {
                System.out.println("Invalid move.");
            }

            // Reset selection
            selectedPiece = null;
            selectedRow = -1;
            selectedCol = -1;

            // Refresh the board to show the move
            displayBoard();
        }
    }

    private List<int[]> getValidMoves(ChessPiece piece, int row, int col) {
        List<int[]> moves = new ArrayList<>();
        switch (piece.getType()) {
            case Pawn:
                addPawnMoves(moves, piece, row, col);
                break;
            case King:
                addKingMoves(moves, row, col);
                break;
            case Rook:
                addStraightLineMoves(moves, row, col);
                break;
            case Knight:
                addKnightMoves(moves, row, col);
                break;
            case Bishop:
                addDiagonalMoves(moves, row, col);
                break;
            case Queen:
                addStraightLineMoves(moves, row, col);
                addDiagonalMoves(moves, row, col);
                break;
            default:
                break;
        }
        return moves;
    }

    private void addPawnMoves(List<int[]> moves, ChessPiece piece, int row, int col) {
        int direction = piece.getColor() == PieceColor.White ? -1 : 1;
        int startRow = piece.getColor() == PieceColor.White ? 6 : 1;
        if (isInBounds(row + direction, col) && board[row + direction][col] == null)
            moves.add(new int[]{row + direction, col});
        if (row == startRow && isInBounds(row + 2 * direction, col) && board[row + 2 * direction][col] == null)
            moves.add(new int[]{row + 2 * direction, col});
        // Capture moves
        if (isInBounds(row + direction, col - 1) && board[row + direction][col - 1] != null)
            moves.add(new int[]{row + direction, col - 1});
        if (isInBounds(row + direction, col + 1) && board[row + direction][col + 1] != null)
            moves.add(new int[]{row + direction, col + 1});
    }

    private void addKingMoves(List<int[]> moves, int row, int col) {
        int[][] kingMoves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] move : kingMoves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            if (isInBounds(newRow, newCol)) moves.add(new int[]{newRow, newCol});
        }
    }

    private void addStraightLineMoves(List<int[]> moves, int row, int col) {
        for (int i = row + 1; i < BOARD_SIZE; i++) if (addMove(moves, i, col)) break;
        for (int i = row - 1; i >= 0; i--) if (addMove(moves, i, col)) break;
        for (int i = col + 1; i < BOARD_SIZE; i++) if (addMove(moves, row, i)) break;
        for (int i = col - 1; i >= 0; i--) if (addMove(moves, row, i)) break;
    }

    private void addKnightMoves(List<int[]> moves, int row, int col) {
        int[][] knightMoves = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        for (int[] move : knightMoves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            if (isInBounds(newRow, newCol)) moves.add(new int[]{newRow, newCol});
        }
    }

    private void addDiagonalMoves(List<int[]> moves, int row, int col) {
        for (int i = 1; i < BOARD_SIZE; i++) if (addMove(moves, row + i, col + i)) break;
        for (int i = 1; i < BOARD_SIZE; i++) if (addMove(moves, row + i, col - i)) break;
        for (int i = 1; i < BOARD_SIZE; i++) if (addMove(moves, row - i, col + i)) break;
        for (int i = 1; i < BOARD_SIZE; i++) if (addMove(moves, row - i, col - i)) break;
    }

    private boolean addMove(List<int[]> moves, int row, int col) {
        if (!isInBounds(row, col)) return true;
        moves.add(new int[]{row, col});
        return board[row][col] != null;
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
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
}
