package model;

import java.util.ArrayList;
import java.util.Arrays;
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



    public static class KnapsackResult{

        public final Item[] sortedItems;//Lista inicial de articulos
        public final List<Item> selectedItems;//elementos agregados en la mochila
        private final double maxValue;//ganancia maxima
        private final double maxWeight;//peso maximo
        private final int capacity;
        private final long nanoTime;

        public KnapsackResult(Item[] sortedItems, List<Item> selectedItems, double maxValue, double maxWeight, int capacity, long nanoTime) {
            this.sortedItems = sortedItems;
            this.selectedItems = selectedItems;
            this.maxValue = maxValue;
            this.maxWeight = maxWeight;
            this.capacity = capacity;
            this.nanoTime = nanoTime;
        }
    }

    public static knapsackResult knapsackSolve(Item[] items, int capacity) {
         long t1 = System.nanoTime();

         //Copiar y ordenar el arreglo por ratio v/w descendente
        Item[] sortedItems = items.clone();
        Arrays.sort(sortedItems, (a, b) -> Double.compare(b.getRatio(), a.getRatio()));



    }

    public static void bubbleSort(Item[] arr) {
        int n = arr.length;
        boolean swapped;

        // Outer loop for each pass through the array
        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Inner loop to compare adjacent elements
            // The last i elements are already in place
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].getRatio() < arr[j + 1].getRatio()) {
                    // Swap elements
                    Item temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // OPTIMIZATION: If no two elements were swapped by inner loop,
            // then the array is already sorted.
            if (!swapped) break;
        }
    }
}
