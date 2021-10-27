package com.company;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CustomerHandler {
    ManagerHandler mngh;
    Scanner sc = new Scanner(System.in);
    private double coins = 0;
    SnackImporter si = new SnackImporter();

    public CustomerHandler(ManagerHandler mngh) {
        this.mngh = mngh;
    }

    public void insertCoins() {
        System.out.println("How much coins do you want to insert? ");
        coins = sc.nextDouble();
    }

    public void printAllSnacks() {
        System.out.println(mngh.getSnacks().size());
        for (Snacks a : mngh.getSnacks()) {
            if (a.getNumberOfSnacks() == 0) {
                System.out.println("No more Snacks");
            } else {
                System.out.println("\nSnack: " + a.getSnackName());
                System.out.println("Price: " + a.getSnackPrice() + "0CHF");
                System.out.println("Number of Snacks: " + a.getNumberOfSnacks());
                System.out.println("------------------------\n");

            }
        }
    }

    public void printUserBalance() {
        System.out.println("Your current balance: " + coins + "0CHF");
    }

    public void buySnack() {
        System.out.println("Which snack do you want to buy? ");
        String boughtSnack = IO.readString();

        for (Snacks a : mngh.getSnacks()) {
            if (a.getSnackName().contains(boughtSnack)) {
                if (coins < a.getSnackPrice()) {
                    System.out.println("You have not enough money");
                } else if (coins > a.getSnackPrice()) {
                    System.out.println("Successfully bought snack");
                    a.setNumberOfSnacks(a.getNumberOfSnacks() - 1);
                    coins = coins - a.getSnackPrice();
                } else if (a.getNumberOfSnacks() == 0) {
                    System.out.println("No more " + a.getSnackName());
                    break;
                }
            }
            if (!si.grabData("SELECT * FROM snack WHERE snackName = '" + a.getSnackName() + "'").isEmpty()) {
                si.updateData("UPDATE snack SET snackPrice = '" + a.getSnackPrice() + "', numberOfSnacks = '" + a.getNumberOfSnacks() + "' WHERE snackName = '" + a.getSnackName() + "';");
            } else {
                si.pushData("INSERT INTO snack (snackName, snackPrice, numberOfSnacks) VALUES ('" + a.getSnackName() + "', '" + a.getSnackPrice() + "', '" + a.getNumberOfSnacks() + "');");
            }
        }
    }


    public void changeUser() {
        IO.drawBox(25, "1. Manager");
        IO.drawBox(25, "2. Customer ");

        int answer3 = IO.readInt();

        switch (answer3) {
            case 1 -> mngh.usrh.snackManager();
            case 2 -> mngh.usrh.customerManager();
        }
    }

}
