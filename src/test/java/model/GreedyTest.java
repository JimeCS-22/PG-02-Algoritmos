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

}