package model;

public class SudokuSolver {
    private final int n = 9; // Tamaño del Sudoku
    private int[][] solution;
    private final int[][] originalBoard;

    public SudokuSolver() {
        int[][] board = randomBoard();
        this.originalBoard = copyBoard(board);
        solveSudoku(board, 0, 0);
    }

    public int[][] getOriginalBoard() {
        return originalBoard;
    }

    public int[][] getSolution() {
        return solution;
    }

    private int[][] copyBoard(int[][] board) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            copy[i] = board[i].clone(); // Clonar cada fila individualmente
        }
        return copy;
    }

    private int[][] randomBoard() {
        int[][] board = new int[n][n];
        int hints = 0;
        while (hints < 17) {
            int num = util.Utility.random(1, 9);
            int row = util.Utility.random(0, 8);
            int col = util.Utility.random(0, 8);
            if (board[row][col] == 0 && isValid(board, row, col, num)) {
                board[row][col] = num;
                hints++;
            }
        }
        return board;
    }

    private boolean solveSudoku(int[][] board, int row, int col) {
        if (row == n) {
            this.solution = board;
            return true;
        }

        if (col == n) return solveSudoku(board, row + 1, 0);

        if (board[row][col] != 0) return solveSudoku(board, row, col + 1);

        for (int num = 1; num <= n; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board, row, col + 1)) return true;
                board[row][col] = 0; //BACKTRACK
            }
        }

        return false;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        //Verificar num no se repite, fila y columna
        for (int i = 0; i < n; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }
        //Verificar num no se repite, bloque 3x3
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[startRow + i][startCol + j] == num) return false;
        return true;
    }
}