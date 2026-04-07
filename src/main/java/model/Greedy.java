package model;

import java.lang.reflect.Array;
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
        List<String> usadas = new ArrayList<>();

        if (monto <= 0) {
            return usadas;
        }

        for (int valorMoneda : monedas) {
            int cantidad = monto / valorMoneda;
            if (cantidad > 0) {
                int subtotal = cantidad * valorMoneda;
                monto -= subtotal;
                usadas.add(valorMoneda + " x " + cantidad + " = " + subtotal + " (resta " + monto + ")");
            }
        }

        return usadas;
    }
    //Complejidad 0(monto / monto_moneda)
    //Con denominación
    public static class KnapsackResult{
        private final Item[] sortedItems;
        private final List<Item> selected;
        private final double maxValue;
        private final int maxWeight;
        private final int capacity;
        private final long nanoTime;

        public KnapsackResult(Item[] sortedItems, List<Item> selected, double maxValue, int maxWeight, int capacity, long nanoTime) {
            this.sortedItems = sortedItems;
            this.selected = selected;
            this.maxValue = maxValue;
            this.maxWeight = maxWeight;
            this.capacity = capacity;
            this.nanoTime = nanoTime;
        }
    }
    //Este método recibe una lista de items(clase en model) y un número de capacidad
    public static KnapsackResult knapsackSolve(Item[] items, int capacity){//items viene desordenada
        long t1 = System.nanoTime();

        //copiar y ordenar el arreglo por ratio v/w descendente para que el menor quede de primero
        Item[] sortedItems = items.clone();
        //Arrays.sort(sortedItems,(a,b) -> Double.compare(a.getRatio(), b.getRatio()));//compara por la relación valor/peso
        bubbleSort(sortedItems);

        List<Item> selected = new ArrayList<>();
        double totalValue = 0;
        int totalWeight = 0;
        int remainingCapacity = capacity;

        for (Item item : sortedItems) {
          if (remainingCapacity <= 0) {break;}

          if (item.getWeight() <= remainingCapacity) {
              //puede tomar el item y agregarlo en la mochila
            selected.add(new Item(item.getName(), item.getWeight(), item.getValue()));
            totalValue += item.getValue();
            totalWeight += item.getWeight();
            remainingCapacity -= item.getWeight();//resto el peso agregado
          }

        }

        long nano = System.nanoTime() - t1;


        return new KnapsackResult(sortedItems, selected, totalValue, totalWeight, capacity, nano);

    }

    public static void bubbleSort(Item[] arr) {
        int n = arr.length;
        boolean swapped;

        // Outer loop for each pass
        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Inner loop to compare adjacent elements
            // (n - i - 1) ensures we don't re-check already sorted end elements
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].getRatio() < arr[j + 1].getRatio()) { //poner el .getRadio es tropicalizar cambie el < por >
                    // Swap elements arr[j] and arr[j + 1]
                    Item temp = arr[j]; //cambie int por Item
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // Optimization: if no two elements were swapped, array is sorted
            if (!swapped) break;
        }
    }
}
