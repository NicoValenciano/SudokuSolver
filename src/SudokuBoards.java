import java.util.*;

public class SudokuBoards {
    private static final int GRID_SIZE = 9;

    static int[][] generateEasyBoard() {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        fillBoard(board);
        removeRandomCells(board, 35);
        return board;
    }

    static int[][] generateMediumBoard() {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        fillBoard(board);
        removeRandomCells(board, 50);
        return board;
    }

    static int[][] generateHardBoard() {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        fillBoard(board);
        removeRandomCells(board, 65);
        return board;
    }

    private static void fillBoard(int[][] board) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(numbers);

        int index = 0;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (index < numbers.size()) {
                    board[row][col] = numbers.get(index++);
                } else {
                    board[row][col] = 0;
                }
            }
        }

        SudokuSolver.solveSudoku(board);
    }

    private static void removeRandomCells(int[][] board, int numCellsToRemove) {
        Random random = new Random();
        int cellsRemoved = 0;

        while (cellsRemoved < numCellsToRemove) {
            int row = random.nextInt(GRID_SIZE);
            int col = random.nextInt(GRID_SIZE);

            if (board[row][col] != 0) {
                board[row][col] = 0;
                cellsRemoved++;
            }
        }
    }
}
