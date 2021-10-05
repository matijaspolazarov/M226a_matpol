package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Snackautomat {

    UserManager snm;

    public Snackautomat(UserManager snm) {
        this.snm = snm;
    }

    ArrayList<Snacks> snacks = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int j = 1;
    private double coins = 0;
    private int password = 8630;
    private int passwordAnswer;

    public void insertCoins() {
        System.out.println("How much coins do you want to insert? ");
        coins = sc.nextDouble();
    }

    public void printUserBalance() {
        System.out.println("Your current balance: " + coins + "0CHF");
    }

    public void addSnack() {
        String snackName;
        double snackPrice;
        int numberOfSnacks;

        System.out.println("How much Snacks do you want to add?");
        int userInput = IO.readInt();

        for (int i = 0; i < userInput; i++) {
            System.out.println("Name of snack: ");
            snackName = IO.readString();

            System.out.println("Price of snack: ");
            snackPrice = sc.nextDouble();

            System.out.println("Number of snacks");
            numberOfSnacks = IO.readInt();

            snacks.add(new Snacks(snackName, snackPrice, numberOfSnacks));
        }
    }

    public void buySnack() {
        System.out.println("Which snack do you want to buy? ");
        String boughtSnack = IO.readString();

        for (Snacks a : snacks) {
            if (a.snackName.contains(boughtSnack)) {
                if (coins < a.snackPrice) {
                    System.out.println("You have not enough money");
                } else if (coins > a.snackPrice) {
                    System.out.println("Successfully bought snack");
                    a.numberOfSnacks--;
                    coins = coins - a.snackPrice;
                } else if (a.numberOfSnacks == 0) {
                    System.out.println("No more " + a.snackName);
                    break;
                }
            }
        }
    }

    public void printAllSnacks() {
        for (Snacks a : snacks) {
            if (a.numberOfSnacks == 0) {
                System.out.println("No more Snacks");
            } else {
                System.out.println("\nSnack: " + a.snackName);
                System.out.println("Price: " + a.snackPrice + "0CHF");
                System.out.println("Number of Snacks: " + a.numberOfSnacks);
                System.out.println("------------------------\n");
            }
        }
    }

    public void deleteSnack() {
        System.out.println("How much items do you want to remove? ");
        int numsOfRemovedItems = IO.readInt();

        for (int i = 0; i < numsOfRemovedItems; i++) {
            System.out.println("Name of the snack");
            String nameOfSnack = IO.readString();

            for (int k = 0; k < snacks.size(); k++) {
                if (snacks.get(k).snackName.contains(nameOfSnack)) {
                    snacks.remove(k);
                }
            }
        }
    }

    public void editPrice() {
        System.out.println("How much prices do you want to edit? ");
        int amountOfPrices = IO.readInt();

        for (int i = 0; i < amountOfPrices; i++) {


            System.out.println("What's the name of the snack?");
            String nameOfSnack = IO.readString();

            System.out.println("Your new price: ");
            int newPriceOfSnack = IO.readInt();

            for (int k = 0; k < snacks.size(); k++) {
                if (snacks.get(k).snackName.contains(nameOfSnack)) {
                    snacks.get(k).snackPrice = newPriceOfSnack;
                }
            }
        }
    }

    public void fillAllSnacks() {
        snacks.add(new Snacks("Snickers", 2.50, 2));
        snacks.add(new Snacks("Mars", 3, 2));
        snacks.add(new Snacks("Oreo", 5, 2));

        System.out.println("Successfully added Snacks to Vending Machine");
    }

    public int changePin() {
        System.out.println("Current Pin: ");
        int pinAnswer = sc.nextInt();

        while (pinAnswer != password) {
            System.out.println("Wrong input, try again");
            pinAnswer = IO.readInt();
        }
        System.out.println("New Pin: ");
        int newPin = IO.readInt();
        password = newPin;

        System.out.println("Confirm your Password");
        int confirmedPin = IO.readInt();

        while (confirmedPin != newPin) {
            System.out.println("The passwords do no match, try again!");
            confirmedPin = IO.readInt();
        }
        System.out.println("Successfully changed password");

        return password;
    }

    public void changeUser() {
        IO.drawBox(25, "1. Manager");
        IO.drawBox(25, "2. Customer ");

        int answer3 = IO.readInt();

        switch (answer3) {
            case 1 -> snm.snackManager();
            case 2 -> snm.customerManager();
        }
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getPasswordAnswer() {
        return passwordAnswer;
    }

    public void setPasswordAnswer(int passwordAnswer) {
        this.passwordAnswer = passwordAnswer;
    }
}
