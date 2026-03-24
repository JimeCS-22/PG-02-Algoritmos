package model;

public class NQueenProblem {

    public String solveNQueens(int n) {

        String result = "";
        int [][] board = new int[n][n];

        if (placeQueens(board, 0)){

            result += printBoard(board);
        }else result+="No solution found";

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

        //Caso base: cuando todas las reinas han sido colocadas
        if (col == board.length) return true;

        //Colocamos la reina en la columna actual
        for (int row = 0; row<board.length; row++){

            if(isSafe(board, row, col)){

                board[row][col] = 1;//coloque la reina

                //ahora colocamos las reinas restantes
                if (placeQueens(board, col+1)) return true;
                //Si no se pudo colocar la reina, retrocedemos y continuar probando
                board[row][col] = 0;
            }


        }

        //si llego aqui, significa que no encontro una solucion
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


}
