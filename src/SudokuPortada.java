import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuPortada extends JFrame {
    public SudokuPortada() {
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Crear panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Agregar título "Sudoku"
        JLabel titleLabel = new JLabel("Sudoku Solver");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20, 0, 20, 0);
        mainPanel.add(titleLabel, constraints);

        // Agregar leyenda "Elija el nivel de dificultad"
        JLabel difficultyLabel = new JLabel("Elija el nivel de dificultad");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 20, 0);
        mainPanel.add(difficultyLabel, constraints);

        // Agregar botones de dificultad
        JButton easyButton = new JButton("Fácil");
        easyButton.addActionListener(new DifficultyButtonListener("Fácil"));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(easyButton, constraints);

        JButton mediumButton = new JButton("Medio");
        mediumButton.addActionListener(new DifficultyButtonListener("Medio"));
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(mediumButton, constraints);

        JButton hardButton = new JButton("Difícil");
        hardButton.addActionListener(new DifficultyButtonListener("Difícil"));
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(hardButton, constraints);

        add(mainPanel);
        setVisible(true);
    }

    private class DifficultyButtonListener implements ActionListener {
        private String difficulty;

        public DifficultyButtonListener(String difficulty) {
            this.difficulty = difficulty;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int[][] board;
            switch (difficulty) {
                case "Fácil":
                    board = SudokuBoards.generateEasyBoard();
                    break;
                case "Medio":
                    board = SudokuBoards.generateMediumBoard();
                    break;
                case "Difícil":
                    board = SudokuBoards.generateHardBoard();
                    break;
                default:
                    return;
            }

            // Crear y mostrar la pantalla SudokuSolverGUI con el tablero precargado
            SudokuSolverGUI solverGUI = new SudokuSolverGUI(board);
            solverGUI.setVisible(true);
            dispose(); // Cerrar la pantalla de portada
        }
    }
}

