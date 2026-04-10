package model;

import util.Utility;

public class SudokuGenerator {

    private final int n = 9;
    
    public int[][] generateBoard(int hints) {
        if (hints < 0) hints = 0;
        if (hints > 81) hints = 81;

        int[][] board = new int[n][n];
        int placed = 0;

        // Evitar loops infinitos si hints es alto
        int maxAttempts = 50_000;
        int attempts = 0;

        while (placed < hints && attempts < maxAttempts) {
            attempts++;

            int num = Utility.random(1, 9);
            int row = Utility.random(0, 8);
            int col = Utility.random(0, 8);

            if (board[row][col] == 0 && isValid(board, row, col, num)) {
                board[row][col] = num;
                placed++;
            }
        }

        return board;
    }

    public boolean isValid(int[][] board, int row, int col, int num) {
        // fila y columna
        for (int i = 0; i < n; i++) {
            if (board[row][i] == num) return false;
            if (board[i][col] == num) return false;
        }

        // bloque 3x3
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[r][c] == num) return false;
            }
        }

        return true;
    }
}