package com.company;

import java.util.Arrays;
import java.util.Collections;

public class GreedySolution {
    public static void change(int[] coins, int amount) {
        Arrays.sort(coins);
        Integer[] bigCoins = new Integer[coins.length];
        for (int i = 0; i < coins.length; i++) {
            bigCoins[i] = coins[i];
        }
        Collections.reverse(Arrays.asList(bigCoins));
        Change.change(amount, bigCoins);
    }
}
