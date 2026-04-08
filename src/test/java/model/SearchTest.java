package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    @Test
    void searchTest(){

        System.out.println("\n--- BUSQUEDA RECURSIVA ---");


        int [] sortedArray = util.Utility.generatedSorted(20,50);
        int value = new Random().nextInt(50); // Genera un valor aleatorio entre 0 y 50
        int pos = Search.binarySearch(sortedArray, value, 0, sortedArray.length - 1);

        System.out.println(Arrays.toString(sortedArray));
        System.out.println("Pos: " + pos + " value:  " + value );

        for (String s : Search.steps) {
            System.out.println(s);
        }
    }

    @Test
    void binarySearchIterativeTest() {

        testWithSize(20);
        testWithSize(35);
        testWithSize(50);
    }

    private void testWithSize(int size) {

        int[] sortedArray = util.Utility.generatedSorted(size, 50);
        int value = new Random().nextInt(50);

        System.out.println("\n==============================");
        System.out.println("Tamaño del arreglo: " + size);
        System.out.println(Arrays.toString(sortedArray));
        System.out.println("Valor a buscar: " + value);

        // ITERATIVO
        System.out.println("\n--- BUSQUEDA ITERATIVA ---");
        Search.steps.clear();
        int posIt = Search.binarySearchIterative(sortedArray, value);

        System.out.println("Pos: " + posIt);

        for (String s : Search.steps) {
            System.out.println(s);
        }
    }

}