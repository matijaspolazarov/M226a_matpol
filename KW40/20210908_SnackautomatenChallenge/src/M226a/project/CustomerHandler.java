package M226a.project;

import java.util.Scanner;

/**
 * This class handles the Customer. All the functions for the customer are created here.
 */
public class CustomerHandler {
    private double coins = 0;

    private ManagerHandler mh;
    private SnackImporter si = new SnackImporter();
    private PrintSnackMachine ps = new PrintSnackMachine();

    //aggregation
    public CustomerHandler(ManagerHandler mngh) {
        this.mh = mngh;
    }

    /**
     * The function insertCoins() is for the user to buy a snack. This function just gets the userinput on how much coins
     * he will insert into the snackMachine.
     */
    public void insertCoins() {
        System.out.println("How much coins do you want to insert? ");
        coins = IO.readDouble();
    }

    /**
     * The function printUserBalance() is to print the current user balance of his wallet / coins.
     * This function just prints out the coins.
     */
    public void printUserBalance() {
        System.out.println("Your current balance: " + coins + "0CHF");
    }

    /**
     * This function is for the user to buy a snack. It checks if you have enough coins to buy a snack. If you have not
     * enough coins the program will print "You have not enough money". The function also checks if there is a snack you
     * want and if there are enough snacks (numberOfSnacks). After this process the function updates or pushes the
     * whole thing.
     */
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

    /**
     * This function is to change the user. It's solved with a switch-case.
     */
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
