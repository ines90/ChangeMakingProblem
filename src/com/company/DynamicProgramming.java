package com.company;

import java.util.Arrays;

public class DynamicProgramming {

    public static void change(int[] coins, int amount) {
        Arrays.sort(coins);
        SubSolution optimalSubSolutions[][] = new SubSolution[amount + 1][coins.length];
        for (int i = 0; i < coins.length; i++) {
            optimalSubSolutions[0][i] = new SubSolution(true, 0, 0, 0);
        }
        for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {
            for (int currentMaxCoin = 0; currentMaxCoin < coins.length; currentMaxCoin++) {
                optimalSubSolutions[currentAmount][currentMaxCoin] = new SubSolution(false, 0, 0, 0);
                if (currentAmount >= coins[currentMaxCoin]) {
                    optimalSubSolutions[currentAmount][currentMaxCoin].possible = optimalSubSolutions[currentAmount - coins[currentMaxCoin]][currentMaxCoin].possible;
                    optimalSubSolutions[currentAmount][currentMaxCoin].coinCount = optimalSubSolutions[currentAmount - coins[currentMaxCoin]][currentMaxCoin].coinCount + 1;
                    optimalSubSolutions[currentAmount][currentMaxCoin].amount = currentAmount - coins[currentMaxCoin];
                    optimalSubSolutions[currentAmount][currentMaxCoin].coin = currentMaxCoin;
                }
                if (currentMaxCoin > 0 &&
                        (!optimalSubSolutions[currentAmount][currentMaxCoin].possible ||
                                optimalSubSolutions[currentAmount][currentMaxCoin - 1].coinCount < optimalSubSolutions[currentAmount][currentMaxCoin].coinCount)) {
                    optimalSubSolutions[currentAmount][currentMaxCoin].possible = optimalSubSolutions[currentAmount][currentMaxCoin - 1].possible;
                    optimalSubSolutions[currentAmount][currentMaxCoin].coinCount = optimalSubSolutions[currentAmount][currentMaxCoin - 1].coinCount;
                    optimalSubSolutions[currentAmount][currentMaxCoin].amount = currentAmount;
                    optimalSubSolutions[currentAmount][currentMaxCoin].coin = currentMaxCoin - 1;
                }
            }
        }
        if (!optimalSubSolutions[amount][coins.length - 1].possible) {
            System.out.println("No solution with specified coins.");
        } else {
            int coinsCount[] = new int[coins.length];
            for (int i = 0; i < coinsCount.length; i++) {
                coinsCount[i] = 0;
            }
            int currentCoin = coins.length - 1;
            for (int remainingAmount = amount; remainingAmount > 0; ) {
                if (optimalSubSolutions[remainingAmount][currentCoin].amount != remainingAmount) {
                    coinsCount[currentCoin]++;
                }
                remainingAmount = optimalSubSolutions[remainingAmount][currentCoin].amount;
                currentCoin = optimalSubSolutions[remainingAmount][currentCoin].coin;
            }

            for (int i = 0; i < coinsCount.length; i++) {
                System.out.println(coins[i] + " " + coinsCount[i]);
            }
        }
    }

    private static class SubSolution {
        public boolean possible;
        public int coinCount;
        public int amount; // Reference to amount of the best solution we came here from
        public int coin; // Reference to greatest coin of the best solution we came here from

        public SubSolution(boolean possible, int coinCount, int amount, int coin) {
            this.possible = possible;
            this.coinCount = coinCount;
            this.amount = amount;
            this.coin = coin;
        }
    }
}
