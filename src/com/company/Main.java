package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] arg) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Give the amount: ");
        int amount = 0;
        try {
            amount = Integer.valueOf(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("String " + e.getMessage().substring(18) + " is not a number.");
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Give denominations separated by spaces: ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = new String[0];
        try {
            s = reader.readLine().split(" ");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        int[] coins = new int[s.length];
        for (int k = 0; k < s.length; k++) {
            try {
                coins[k] = Integer.parseInt(s[k]);
            } catch (NumberFormatException e) {
                System.out.println("String " + e.getMessage().substring(18) + " is not a number.");
                return;
            }
        }
        System.out.println("Greedy solution: ");
        GreedySolution.change(coins, amount);
        System.out.println("Dynamic programming: ");
        DynamicProgramming.change(coins, amount);
    }
}

