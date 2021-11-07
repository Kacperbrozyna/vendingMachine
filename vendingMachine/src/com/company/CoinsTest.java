package com.company;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CoinsTest {

    Coins testCoin = new Coins();


    @Test
    void addUpCoins() {
        // coins that would get added up together
        int[] testMoney = {1, 2, 0, 0, 0, 0, 1};
        assertEquals(new BigDecimal("2.01"), testCoin.addUpCoins(testMoney, new BigDecimal("0")));
    }

    @Test
    void divideIntoCoins() {
        // divided into -> pounds, fifty, twenty, ten, five, two , one
        int[] testMoney = {0, 1, 0, 0, 1, 1, 1};
        assertArrayEquals(testMoney, testCoin.divideIntoCoins(new BigDecimal("0.58"))); //testing if the correct coins are returned
    }
}