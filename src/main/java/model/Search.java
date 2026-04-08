package model;

import java.util.ArrayList;
import java.util.List;

public class Search {

    public static List<String> steps = new ArrayList<>();

    /**
     * int value = 10
     * int pos =
    * binarySearch(sortedArray, value,0,sortedArray.length-1)
    * */

    public static int binarySearch(int [] sortedArray, int value, int low, int high){

        //Caso base
        if (low > high) {
            steps.add("No se encontró el valor " + value);
            return -1;
        }

        int mid = (low+high)/2;

        steps.add("Rango [" + low + " , " + high + " ] ----> mitad  = " + mid + " (sortedArray[mid] = " +
                sortedArray[mid] + " )");

        if (value == sortedArray[mid]){
            steps.add("Valor encontrado en el indice " + mid);
            return mid;
        } else if (value < sortedArray[mid]) {
            return binarySearch(sortedArray, value, low, mid - 1);
        } else return binarySearch(sortedArray, value, mid + 1, high);

    }

    /**
     * Metodo iteractivo de la busqueda binaria
     **/

    public static int binarySearchIterative(int [] sortedArray, int value){

        int low = 0;
        int high = sortedArray.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            steps.add("Rango [" + low + " , " + high + " ] ----> mitad  = " + mid + " (sortedArray[mid] = " +
                    sortedArray[mid] + " )");

            if (value == sortedArray[mid]) {
                steps.add("Valor encontrado en el indice " + mid);
                return mid;
            } else if (value < sortedArray[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        steps.add("No se encontró el valor " + value);
        return -1;
    }

    /**
     * Metodo de busqueda binaria Secuencial
     **/
}
