package model;

import org.junit.jupiter.api.Test;



class NQueenProblemTest {

    @Test
    void solveNQueensTest() {
        NQueenProblem nQueenProblem = new NQueenProblem();

        System.out.println("Problemas de las N reinas.");
        System.out.println("Solución para N=4");
        System.out.println(nQueenProblem.solveNQueens(4));

        System.out.println("Problemas de las N reinas. " );
        System.out.println("Solución para N=8");
        System.out.println(nQueenProblem.solveNQueens(8));

    }

}