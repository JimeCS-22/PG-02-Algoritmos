package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuSolverTest {
    @Test
    void sudokuTest() {
        SudokuSolver sudoku = new SudokuSolver(17);

        System.out.println("Sudoku - Tablero Inicial\n");
        System.out.println(prettyPrint(sudoku.getOriginalBoard()));

        int[][] sol = sudoku.getSolution();
        if (sol == null) {
            System.out.println("No se pudo encontrar solución.");
            return;
        }

        System.out.println("\nSudoku - Solución\n");
        System.out.println(prettyPrint(sol));
    }

    private String prettyPrint(int[][] b) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 9; r++) {
            if (r % 3 == 0) sb.append("+-------+-------+-------+\n");
            for (int c = 0; c < 9; c++) {
                if (c % 3 == 0) sb.append("| ");
                sb.append(b[r][c] == 0 ? ". " : (b[r][c] + " "));
            }
            sb.append("|\n");
        }
        sb.append("+-------+-------+-------+\n");
        return sb.toString();
    }
}