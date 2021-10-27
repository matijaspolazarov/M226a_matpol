package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerHandler {

    UserHandler usrh;
    SnackImporter si = new SnackImporter();

    private ArrayList<Snacks> snacks = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int j = 1;
    private double coins = 0;
    private int password = 8630;
    private int passwordAnswer;

    public ManagerHandler(UserHandler usrh) {
        this.usrh = usrh;
    }

    public void addSnack() {

        String snackName;
        double snackPrice;
        long numberOfSnacks;

        System.out.println("How much Snacks do you want to add?");
        int userInput = IO.readInt();

        for (int i = 0; i < userInput; i++) {
            System.out.println("Name of snack: ");
            snackName = IO.readString();

            System.out.println("Price of snack: ");
            snackPrice = IO.readDouble();

            System.out.println("Number of snacks");
            numberOfSnacks = (long) IO.readInt();

            snacks.add(new Snacks(snackName, snackPrice, numberOfSnacks));


            if(!si.grabData("SELECT * FROM snack WHERE snackName = '" + snackName + "'").isEmpty()){
                si.updateData("UPDATE snack SET snackPrice = '" + snackPrice + "', numberOfSnacks = '" + numberOfSnacks + "' WHERE snackName = '" + snackName + "';");
            }else{
                si.pushData("INSERT INTO snack (snackName, snackPrice, numberOfSnacks) VALUES ('" + snackName + "', '" +  snackPrice + "', '" + numberOfSnacks + "');"); }
        }
    }


    public void buySnack() {
        System.out.println("Which snack do you want to buy? ");
        String boughtSnack = IO.readString();

        for (Snacks a : snacks) {
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
        }
    }


    public void deleteSnack() {
        System.out.println("How much items do you want to remove? ");
        int numsOfRemovedItems = IO.readInt();

        for (int i = 0; i < numsOfRemovedItems; i++) {
            System.out.println("Name of the snack");
            String nameOfSnack = IO.readString();

            for (int k = 0; k < snacks.size(); k++) {
                if (snacks.get(k).getSnackName().contains(nameOfSnack)) {
                    snacks.remove(k);
                }
                si.deleteData("DELETE FROM snack WHERE snackName = '" + nameOfSnack + "';");
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
                if (snacks.get(k).getSnackName().contains(nameOfSnack)) {
                    snacks.get(k).setSnackPrice(newPriceOfSnack);
                }
            }
        }
    }

    public void fillAllSnacks() {
        snacks.add(new Snacks("Snickers", 2.50, 2L));
        snacks.add(new Snacks("Mars", 3, 2L));
        snacks.add(new Snacks("Oreo", 5, 2L));

        System.out.println("Successfully added Snacks to Vending Machine");
    }

    public int changePin() {
        System.out.println("Current Pin: ");
        int pinAnswer = sc.nextInt();

        while (pinAnswer != password) {
            System.out.println("Wrong password, please try again");
            pinAnswer = IO.readInt();
        }
        System.out.println("New Pin: ");
        int newPin = IO.readInt();
        password = newPin;

        System.out.println("Confirm your Pin");
        int confirmedPin = IO.readInt();

        while (confirmedPin != newPin) {
            System.out.println("The Pins do no match, try again!");
            confirmedPin = IO.readInt();
        }
        System.out.println("Successfully changed Pin");

        return password;
    }

    public void changeUser() {
        IO.drawBox(25, "1. Manager");
        IO.drawBox(25, "2. Customer ");

        int answer3 = IO.readInt();

        switch (answer3) {
            case 1 -> usrh.snackManager();
            case 2 -> usrh.customerManager();
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

    public ArrayList<Snacks> getSnacks() {
        return snacks;
    }

    public void setSnacks(ArrayList<Snacks> snacks) {
        this.snacks = snacks;
    }
}
