package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuSolverTest {
    @Test
    void sudokuTest() {
        SudokuSolver sudoku = new SudokuSolver();
        System.out.println("Sudoku Solver Random Board\n");
        System.out.println(printBoard(sudoku.getOriginalBoard()));
        System.out.println("\nSolution");
        System.out.println(printBoard(sudoku.getSolution()));
    }

    private String printBoard(int[][] board) {
        String result = "";
        int n = board.length;
        for (int[] row : board) {
            for (int col : row)
                result += " " + col;
            result += "\n"; //Salto de linea a la siguiente fila
        }
        return result;
    }
}