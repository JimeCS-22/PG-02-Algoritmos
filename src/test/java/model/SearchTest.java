package model;

import org.junit.jupiter.api.Test;
import util.Utility;

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
            int[] sortedArray = Utility.generatedSorted(size, 200);
            int value = new Random().nextInt(200) + 1;

            System.out.println("\n==============================");
            System.out.println("Tamaño del arreglo: " + size);
            System.out.println(Arrays.toString(sortedArray));
            System.out.println("Valor a buscar: " + value);

            // Secuencial
            System.out.println("\n--- SECUENCIAL ---");
            Search.steps.clear();
            int posLin = Search.linearSearch(sortedArray, value);
            System.out.println("Pos: " + posLin);
            Search.steps.forEach(System.out::println);

            // Secuencial con centinela
            System.out.println("\n--- SECUENCIAL CON CENTINELA ---");
            Search.steps.clear();
            int posSent = Search.sentinelLinearSearch(sortedArray, value);
            System.out.println("Pos: " + posSent);
            Search.steps.forEach(System.out::println);

            // Binaria recursiva
            System.out.println("\n--- BINARIA RECURSIVA ---");
            Search.steps.clear();
            int posRec = Search.binarySearch(sortedArray, value, 0, sortedArray.length - 1);
            System.out.println("Pos: " + posRec);
            Search.steps.forEach(System.out::println);

            // Binaria iterativa
            System.out.println("\n--- BINARIA ITERATIVA ---");
            Search.steps.clear();
            int posIt = Search.binarySearchIterative(sortedArray, value);
            System.out.println("Pos: " + posIt);
            Search.steps.forEach(System.out::println);

            // Interpolación
            System.out.println("\n--- INTERPOLACIÓN ---");
            Search.steps.clear();
            int posInt = Search.interpolationSearch(sortedArray, value);
            System.out.println("Pos: " + posInt);
            Search.steps.forEach(System.out::println);
        }
    }

