import java.util.*;

class SudokuSolver {
    private static final int GRID_SIZE = 9;

    static class Node {
        int value;
        List<Node> neighbors;

        Node() {
            value = 0;
            neighbors = new ArrayList<>();
        }

        void addNeighbor(Node neighbor) {
            neighbors.add(neighbor);
        }
    }

    static boolean solveSudoku(Node[][] board) {
        List<Node> emptyCells = new ArrayList<>();
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col].value == 0) {
                    emptyCells.add(board[row][col]);
                }
            }
        }

        return backtrack(board, emptyCells, 0);
    }

    private static boolean backtrack(Node[][] board, List<Node> emptyCells, int cellIndex) {
        if (cellIndex == emptyCells.size()) {
            return true; // Solución encontrada
        }

        Node cell = emptyCells.get(cellIndex);
        for (int value = 1; value <= GRID_SIZE; value++) {
            if (isValid(board, cell, value)) {
                cell.value = value;
                if (backtrack(board, emptyCells, cellIndex + 1)) {
                    return true;
                }
                cell.value = 0;
            }
        }

        return false;
    }

    private static boolean isValid(Node[][] board, Node cell, int value) {
        int row = -1, col = -1;

        // Encontrar la posición del nodo en la matriz
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (board[i][j] == cell) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // Verificar fila
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i].value == value && board[row][i] != cell) {
                return false;
            }
        }

        // Verificar columna
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col].value == value && board[i][col] != cell) {
                return false;
            }
        }

        // Verificar caja 3x3
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if (board[i][j].value == value && board[i][j] != cell) {
                    return false;
                }
            }
        }

        return true;
    }

/*    private static boolean isValid(Node[][] board, Node cell, int value) {
        // Verificar vecinos
        for (Node neighbor : cell.neighbors) {
            if (neighbor.value == value) {
                return false;
            }
        }

        return true;
    }*/

    // Método para inicializar los vecinos de cada nodo
    static void initializeNeighbors(Node[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Node node = board[row][col];
                for (int i = 0; i < GRID_SIZE; i++) {
                    if (i != col) {
                        node.addNeighbor(board[row][i]);
                    }
                    if (i != row) {
                        node.addNeighbor(board[i][col]);
                    }
                }
                int boxRowStart = row - row % 3;
                int boxColStart = col - col % 3;
                for (int i = boxRowStart; i < boxRowStart + 3; i++) {
                    for (int j = boxColStart; j < boxColStart + 3; j++) {
                        if (i != row || j != col) {
                            node.addNeighbor(board[i][j]);
                        }
                    }
                }
            }
        }
    }
}
