package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GreedyTest {

    @Test
    void TestCoinChange() {

        List<Integer> lista = new ArrayList<>();
        lista = Greedy.coinChange(787);

        for(Integer value : lista){
            System.out.println(value);
        }

    }

    @Test
    void TestCoinChange2() {
        List<String> lista = new ArrayList<>();
        lista = Greedy.coinChange2(787);

        for(String value : lista){
            System.out.println(value);
        }
    }

    @Test
    void TestCoinChange3() {
        List<String> lista = new ArrayList<>();
        lista = Greedy.coinChange2(1024);

        for(String value : lista){
            System.out.println(value);
        }
    }

    @Test
    void TestCoinChange4() {
        List<String> lista = new ArrayList<>();
        lista = Greedy.coinChange2( 960);

        for(String value : lista){
            System.out.println(value);
        }
    }

    @Test
    void TestCoinChange5() {
        List<String> lista = new ArrayList<>();
        lista = Greedy.coinChange2( 2345);

        for(String value : lista){
            System.out.println(value);
        }
    }

    //Probar en una clase de testeo para cada paquete y mochilas con pesos máximos de 15, 12, 10, 7.

    @Test
    void testKnapsack1() {
        Item[] lista = Item.Package1();
        lista = Greedy.knapsackSolve( lista,15).sortedItems;

        System.out.println(" Test Knapsack - Capacity 15");
        for(Item value : lista){
            System.out.println(value);
        }
    }

    @Test
    void testKnapsack2() {
        Item[] lista = Item.Package2();
        lista = Greedy.knapsackSolve( lista,12).sortedItems;

        System.out.println(" Test Knapsack - Capacity: 12");
        for(Item value : lista){
            System.out.println(value);
        }
    }

    @Test
    void testKnapsack3() {
        Item[] lista = Item.Package3();
        lista = Greedy.knapsackSolve( lista,10).sortedItems;

        System.out.println(" Test Knapsack - Capacity: 10");
        for(Item value : lista){
            System.out.println(value);
        }
    }

    @Test
    void testKnapsack4() {
        Item[] lista = Item.Package4();
        lista = Greedy.knapsackSolve( lista,7).sortedItems;

        System.out.println(" Test Knapsack - Capacity: 7");
        for(Item value : lista){
            System.out.println(value);
        }
    }
}