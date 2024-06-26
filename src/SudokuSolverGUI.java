import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolverGUI extends JFrame {
    private JTextField[][] cells;
    private JTextArea solutionArea;
    private JPanel solutionPanel;

    public SudokuSolverGUI() {
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear panel para el tablero de Sudoku
        JPanel boardPanel = new JPanel(new GridLayout(9, 9));
        cells = new JTextField[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new JTextField(1);
                boardPanel.add(cells[row][col]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        // Crear botón para resolver
        JButton solveButton = new JButton("Resolver");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveSudoku();
            }
        });
        add(solveButton, BorderLayout.NORTH);

        // Crear panel para mostrar la solución
        solutionPanel = new JPanel(new GridLayout(9, 9));
        solutionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Crear área de texto para mostrar mensajes
        solutionArea = new JTextArea(5, 20);
        solutionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(solutionArea);
        add(scrollPane, BorderLayout.SOUTH);

        add(solutionPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void solveSudoku() {
        SudokuSolver.Node[][] board = new SudokuSolver.Node[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String value = cells[row][col].getText();
                board[row][col] = new SudokuSolver.Node();
                board[row][col].value = value.isEmpty() ? 0 : Integer.parseInt(value);
            }
        }

        SudokuSolver.initializeNeighbors(board);

        if (SudokuSolver.solveSudoku(board)) {
            displaySolution(board);
        } else {
            solutionArea.setText("No hay solución");
        }
    }

    private void displaySolution(SudokuSolver.Node[][] solution) {
        solutionPanel.removeAll();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JLabel label = new JLabel(String.valueOf(solution[row][col].value));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                solutionPanel.add(label);
            }
        }

        pack();
        revalidate();
    }
}
