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

}