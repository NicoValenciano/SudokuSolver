import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolverGUI extends JFrame {
    private JTextField[][] cells;
    private JButton solveButton;

    public SudokuSolverGUI(int[][] board) {
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Crear panel para el tablero de Sudoku
        JPanel boardPanel = new JPanel(new GridLayout(9, 9));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cells = new JTextField[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new JTextField(1);
                cells[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
                cells[row][col].setPreferredSize(new Dimension(50, 50));
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                if (row < 3 || row > 5) {
                    if (col < 3 || col > 5) {
                        cells[row][col].setBackground(Color.LIGHT_GRAY);
                    }
                } else if (row > 2 && row < 6) {
                    if (col > 2 && col < 6) {
                        cells[row][col].setBackground(Color.LIGHT_GRAY);
                    }
                }
                boardPanel.add(cells[row][col]);
                if (board[row][col] != 0) {
                    cells[row][col].setText(String.valueOf(board[row][col]));
                    cells[row][col].setEditable(false);
                }
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        // Crear botón para resolver
        solveButton = new JButton("Resolver");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolveSudoku();
            }
        });
        add(solveButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void resolveSudoku() {
        int[][] board = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String value = cells[row][col].getText();
                board[row][col] = value.isEmpty() ? 0 : Integer.parseInt(value);
            }
        }

        boolean solved = SudokuSolver.solveSudoku(board);
        if (solved) {
            displaySolution(board);
        } else {
            JOptionPane.showMessageDialog(this, "No hay solución", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displaySolution(int[][] solution) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int userValue = cells[row][col].getText().isEmpty() ? 0 : Integer.parseInt(cells[row][col].getText());
                int solutionValue = solution[row][col];

                if (userValue == solutionValue && userValue != 0) {
                    cells[row][col].setForeground(Color.GREEN);
                } else {
                    cells[row][col].setForeground(Color.RED);
                    cells[row][col].setText(String.valueOf(solutionValue));
                    cells[row][col].setEditable(false);
                }
            }
        }
    }
}
