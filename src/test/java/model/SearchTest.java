package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    @Test
    void searchTest(){

        int [] sortedArray = util.Utility.generatedSorted(20,50);
        int value = new Random().nextInt(50); // Genera un valor aleatorio entre 0 y 50
        int pos = Search.binarySearch(sortedArray, value, 0, sortedArray.length - 1);

        System.out.println(Arrays.toString(sortedArray));
        System.out.println("Pos: " + pos + "value:  " + value );
        if (pos != -1) {
            System.out.println("Valor encontrado en el índice: " + pos);

        } else {
            System.out.println("Valor no encontrado en el arreglo.");

        }
    }

}