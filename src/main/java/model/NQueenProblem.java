package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NQueenProblem {

    public String solveNQueens(int n) {

        String result = "";
        int[][] board = new int[n][n];

        Random rand = new Random();
        int startCol = rand.nextInt(n);

        if (placeQueens(board, startCol)) {
            result += printBoard(board);
        } else {
            result += "No existe solución para un tablero de " + n + "*" + n;
        }

        return result;
    }


    private String printBoard(int [][] board){

        String result = "";

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                result += board[i][j] == 1 ? "Q " : ". ";
            }
            result += "\n";
        }

        return result;
    }

    /*Metodo que permite colocar las reinas dentro del tablero*/
    private boolean placeQueens(int[][] board, int col) {

        // Caso base
        if (col >= board.length || col < 0) return true;

        // Si ya hay reina en esta columna, saltarla
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 1) {
                return placeQueens(board, col + 1);
            }
        }

        for (int row = 0; row < board.length; row++) {

            if (isSafe(board, row, col)) {

                board[row][col] = 1;

                // intentar derecha e izquierda
                if (placeQueens(board, col + 1) && placeQueens(board, col - 1)) {
                    return true;
                }

                board[row][col] = 0;
            }
        }

        return false;
    }

    //Metodo para comprobar si es seguro colocar una reina en la posicion dada
    private boolean isSafe(int[][] board, int row, int col) {

        //primero se comprueba el lado izquierdo de esta fila
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) return false;
        }

        //se comprueba la diagonal superior izquierda
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        // se comprueba la diognal inferior izquierda
        for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 1) return false;
        }

        //se comprueba el lado derecho de esta fila
        for (int i = col + 1; i < board.length; i++) {
            if (board[row][i] == 1) return false;
        }

        //se comprueba la diagonal superior derecha
        for (int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 1) return false;
        }

        //se comprueba la diagonal inferior derecha
        for (int i = row, j = col; i < board.length && j <board.length; i++, j++) {
            if (board[i][j] == 1) return false;
        }


        return true; // Si llego aqui es seguro colocar la reina en esta posicion
    }

    public int[] solveNQueensPositions(int n) {

        int[][] board = new int[n][n];
        Random rand = new Random();
        int startCol = rand.nextInt(n);

        if (placeQueens(board, startCol)) {

            int[] posiciones = new int[n];

            for (int col = 0; col < n; col++) {
                posiciones[col] = -1;

                for (int row = 0; row < n; row++) {
                    if (board[row][col] == 1) {
                        posiciones[col] = row;
                        break;
                    }
                }
            }

            return posiciones;
        }

        return null; // no hay solución
    }


}
