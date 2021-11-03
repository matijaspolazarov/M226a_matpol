package M226a.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used for the managerHandler. Here are all functions for the Manager.
 */
public class ManagerHandler {

    UserHandler usrh;
    private SnackImporter si = new SnackImporter();

    private ArrayList<Snacks> snacks = new ArrayList<>();

    private int pin = 8630;
    private int passwordAnswer;

    /**
     * A constructor for the object.
     * @param usrh
     */
    public ManagerHandler(UserHandler usrh) {
        this.usrh = usrh;
    }

    /**
     * This function is used for the manager to add some individual snacks to the snackMachine. If you add a snack to
     * the snackMachine it'll get updated in the database. If you end the program and start it again, the snack will be
     * be added to the vending machine, because of the connection with the database.
     */
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


            if (!si.grabData("SELECT * FROM snack WHERE snackName = '" + snackName + "'").isEmpty()) {
                si.updateData("UPDATE snack SET snackPrice = '" + snackPrice + "', numberOfSnacks = '" + numberOfSnacks + "' WHERE snackName = '" + snackName + "';");
            } else {
                si.pushData("INSERT INTO snack (snackName, snackPrice, numberOfSnacks) VALUES ('" + snackName + "', '" + snackPrice + "', '" + numberOfSnacks + "');");
            }
        }
    }

    /**
     * The function deleteSnack() is used for the manager to delete a snack if he wants to. Like for the other functions
     * the data of the deleted snack will be also deleted in the database. It is solved with a "DELETE" Statement to
     * delete it from the database.
     */
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

    /**
     * This function is to edit the price. First of all you have to write in a number of how many prices you want to edit.
     * If you want to edit 2 snacks at once, you can write in the number 2 and it will loop it twice. After you edited
     * the price it will automatically be updated in the database. This passage is possible through the update statement.
     */
    public void editPrice() {
        System.out.println("How much prices do you want to edit? ");
        int amountOfPrices = IO.readInt();

        for (int i = 0; i < amountOfPrices; i++) {


            System.out.println("What's the name of the snack?");
            String nameOfSnack = IO.readString();

            System.out.println("Your new price: ");
            double newPriceOfSnack = IO.readDouble();

            for (int k = 0; k < snacks.size(); k++) {
                if (snacks.get(k).getSnackName().contains(nameOfSnack)) {
                    snacks.get(k).setSnackPrice(newPriceOfSnack);
                }
            }
            for (Snacks a : getSnacks()) {


                si.updateData("UPDATE snack SET snackPrice = '" + a.getSnackPrice() + "', numberOfSnacks = '" + a.getNumberOfSnacks() + "' WHERE snackName = '" + a.getSnackName() + "';");
            }
        }
    }

    /**
     * This function is to change the pin, first of all you have to type in the current pin to change the pin to a new
     * one. The reason for returning the value of changePin() is to use it afterwards to log in into the Manager User
     * with the new password. The function checks if the pinAnswer is the same as the password-
     *
     * @return the changed Pin
     */
    public int changePin() {
        System.out.println("Current Pin: ");
        int pinAnswer = IO.readInt();

        while (pinAnswer != pin) {
            System.out.println("Wrong password, please try again");
            pinAnswer = IO.readInt();
        }
        System.out.println("New Pin: ");
        int newPin = IO.readInt();
        pin = newPin;

        System.out.println("Confirm your Pin");
        int confirmedPin = IO.readInt();

        while (confirmedPin != newPin) {
            System.out.println("The Pins do no match, try again!");
            confirmedPin = IO.readInt();
        }
        System.out.println("Successfully changed Pin");

        return pin;

    }

    /**
     * This function is to change the user. If you want to test something out as a Manager it's important, to change the
     * user to "customer" to test it. If you are a customer and there's an error you can switch the user to the manager
     */
    public void changeUser() {
        IO.drawBox(25, "1. Manager");
        IO.drawBox(25, "2. Customer ");

        int answer3 = IO.readInt();

        switch (answer3) {
            case 1 -> usrh.snackManager();
            case 2 -> usrh.customerManager();
        }
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
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
