package com.company;

public class Change {


    static public void change(int amount, Integer coin[]) {


        if (amount < 0) {
            System.out.println("Given negative value");
        }

        if (amount == 0) {
            System.out.println("Given value 0");
        }
        if (amount > 0) {
            for (int i = 0; i < coin.length; i++) {
                int num = amount / coin[i];
                System.out.println(coin[i] + " " + num);
//                amount -= num * coin[i];
                amount = amount % coin[i];
            }
        }

        if (amount > 0) {
            System.out.println("Remaining amount: " + amount);
        }
    }

}
