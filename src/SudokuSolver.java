import java.util.*;

class SudokuSolver {
    private static final int GRID_SIZE = 9;

    private static class Cell {
        int row;
        int col;
        Set<Integer> values;

        Cell(int row, int col, Set<Integer> values) {
            this.row = row;
            this.col = col;
            this.values = values;
        }
    }

    static boolean solveSudoku(int[][] board) {
        List<Cell> cells = new ArrayList<>();
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    Set<Integer> values = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                    cells.add(new Cell(row, col, values));
                }
            }
        }

        return backtrack(board, cells, 0);
    }

    private static boolean backtrack(int[][] board, List<Cell> cells, int cellIndex) {
        if (cellIndex == cells.size()) {
            return true; // Solución encontrada
        }

        Cell cell = cells.get(cellIndex);
        for (int value : cell.values) {
            if (isValid(board, cell.row, cell.col, value)) {
                board[cell.row][cell.col] = value;
                if (backtrack(board, cells, cellIndex + 1)) {
                    return true;
                }
                board[cell.row][cell.col] = 0;
            }
        }

        return false;
    }

    private static boolean isValid(int[][] board, int row, int col, int value) {
        // Verificar fila
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == value) {
                return false;
            }
        }

        // Verificar columna
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == value) {
                return false;
            }
        }

        // Verificar caja 3x3
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        //int boxRowStart = row / 3 * 3;
        //int boxColStart = col / 3 * 3;
        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if (board[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

}
