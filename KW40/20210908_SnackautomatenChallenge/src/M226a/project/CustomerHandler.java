package M226a.project;

import java.util.Scanner;

public class CustomerHandler {
    ManagerHandler mh;
    Scanner sc = new Scanner(System.in);
    private double coins = 0;
    SnackImporter si = new SnackImporter();
    PrintSnackMachine ps = new PrintSnackMachine();

    public CustomerHandler(ManagerHandler mngh) {
        this.mh = mngh;
    }

    public void insertCoins() {
        System.out.println("How much coins do you want to insert? ");
        coins = IO.readDouble();
    }

    public void printAllSnacks() {
        System.out.println(mh.getSnacks().size());
        for (Snacks a : mh.getSnacks()) {
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
        ps.printSnacks(mh.getSnacks(), 3, 25);
        System.out.println("Which snack do you want to buy? ");
        String boughtSnack = IO.readString();

        for (Snacks a : mh.getSnacks()) {
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
            case 1 -> mh.usrh.snackManager();
            case 2 -> mh.usrh.customerManager();
        }
    }

}
