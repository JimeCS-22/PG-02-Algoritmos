package model;

import java.util.ArrayList;
import java.util.List;

public class Greedy {

    private static final int [] monedas = {500,100,50,25,10,5,1};

    public static List<Integer> coinChange(int monto){

        List<Integer> usudas = new ArrayList<>();

        for (int coin : monedas) {
            while (monto >= coin) {
                usudas.add(coin);
                monto -= coin;
            }
        }

        return usudas;

    }
    
    public static List<String> coinChange2(int monto){
        List<String> usudas = new ArrayList<>();

        if (monto <= 0) {
            return usudas;
        }

        for (int valorMoneda : monedas) {
            int cantidad = monto / valorMoneda;
            if (cantidad > 0) {
                int subtotal = cantidad * valorMoneda;
                monto -= subtotal;
                usudas.add(valorMoneda + "*" + cantidad + " = " + subtotal + " (resta " + monto + ")");
            }
        }

        return usudas;
    }
}
