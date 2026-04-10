package model;

public class SudokuSolver {
    private static final int N = 9;

    private int[][] solution;
    private int[][] originalBoard;

    public SudokuSolver() {
        this(17); // mínimo típico
    }

    public SudokuSolver(int hints) {
        SudokuGenerator generator = new SudokuGenerator();

        int[][] board;
        int tries = 0;

        do {
            board = generator.generateBoard(hints);
            originalBoard = copyBoard(board);

            int[][] work = copyBoard(board); // resolver sobre copia
            if (solveSudoku(work, 0, 0)) {
                solution = copyBoard(work);
                return;
            }

            tries++;
        } while (tries < 50);

        // Si llegara aquí (raro), al menos no quedas con nulls inconsistentes
        solution = null;
    }

    public int[][] getOriginalBoard() {
        return copyBoard(originalBoard);
    }

    public int[][] getSolution() {
        return solution == null ? null : copyBoard(solution);
    }

    private int[][] copyBoard(int[][] board) {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    private boolean solveSudoku(int[][] board, int row, int col) {
        if (row == N) return true;
        if (col == N) return solveSudoku(board, row + 1, 0);
        if (board[row][col] != 0) return solveSudoku(board, row, col + 1);

        for (int num = 1; num <= N; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board, row, col + 1)) return true;
                board[row][col] = 0; // backtrack
            }
        }
        return false;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < N; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[startRow + r][startCol + c] == num) return false;
            }
        }
        return true;
    }
}