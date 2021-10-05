package com.company;

public class UserManager {

    Snackautomat sm;
    IO io;

    public UserManager() {
        this.sm = new Snackautomat(this);
        this.io = new IO();
    }

    public void welcomeMessage() {
        switch (io.drawbox()) {
            case 1 -> snackManager();
            case 2 -> customerManager();
        }
    }

    public void snackManager() {
        System.out.println("What's the password? ");
        sm.setPasswordAnswer(IO.readInt());

        while (sm.getPasswordAnswer() != sm.getPassword()) {
            System.out.println("error, try again: ");
            sm.setPasswordAnswer(IO.readInt());
        }

        while (IO.answer2 != 7) {
            IO.drawBox(25, "Hello Manager ");
            System.out.println("");
            IO.drawBox(25, "1. add snack");
            IO.drawBox(25, "2. delete Snack ");
            IO.drawBox(25, "3. edit Price ");
            IO.drawBox(25, "4. change Pin ");
            IO.drawBox(25, "5. change user");
            IO.drawBox(25, "6. fill whole Machine ");
            IO.drawBox(25, "8. End program");

            System.out.println("Please select something");
            int answer2 = IO.readInt();

            switch (answer2) {
                case 1 -> sm.addSnack();
                case 2 -> sm.deleteSnack();
                case 3 -> sm.editPrice();
                case 4 -> sm.changePin();
                case 5 -> sm.changeUser();
                case 6 -> sm.fillAllSnacks();
                case 8 -> System.exit(0);
            }
        }
    }

    public void customerManager() {
        while (IO.answer != 7) {
            IO.drawBox(25, "1. Insert Coins ");
            IO.drawBox(25, "2. print all snacks ");
            IO.drawBox(25, "3. buy snacks ");
            IO.drawBox(25, "4. print current balance");
            IO.drawBox(25, "5. change user");
            IO.drawBox(25, "6. fill all Snacks");
            IO.drawBox(25, "8. end Program");

            System.out.println("\n\nPick a number: ");
            IO.answer = IO.readInt();

            switch (IO.answer) {
                case 1 -> sm.insertCoins();
                case 2 -> sm.printAllSnacks();
                case 3 -> sm.buySnack();
                case 4 -> sm.printUserBalance();
                case 5 -> sm.changeUser();
                case 6 -> sm.fillAllSnacks();
                case 8 -> System.exit(0);
            }
        }
    }
}
