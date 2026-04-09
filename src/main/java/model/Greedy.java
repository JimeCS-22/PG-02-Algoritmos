package model;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Greedy {

    private static final int [] monedas = {500,100,50,25,10,5,1};


    public static class Coin{

        private  int coin;
        private int quantity;
        private int amount;
        private int remaining;//Quantity* coin

        public Coin(int coin, int quantity, int amount, int remaining){
            this.coin = coin;
            this.quantity = quantity;
            this.amount = amount;
            this.remaining = remaining;

        }

        public int getCoin() {return coin;}

        public int getQuantity() {return quantity;}

        public int getAmount() {return amount;}

        public int getRemaining() {return remaining;}

        @Override
        public String toString() {
            return String.format("%d x %d = %d (remaining %d)", coin, quantity, amount, remaining);
        }
    }
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
        public final Item[] sortedItems;//Lista inicial de articulos
        public final List<Item> selectedItems;//elementos agregados en la mochila
        private final double maxValue;//ganancia maxima
        private final double maxWeight;//peso maximo
        private final int capacity;
        private final long nanoTime;

        public KnapsackResult(Item[] sortedItems, List<Item> selectedItems, double maxValue, int maxWeight, int capacity, long nanoTime) {
            this.sortedItems = sortedItems;
            this.selectedItems = selectedItems;
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
            selected.add(new Item(item.getName(), item.getWeight(), item.getValue(), Color.PINK));
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

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].getRatio() < arr[j + 1].getRatio()) {
                    Item temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }
}
