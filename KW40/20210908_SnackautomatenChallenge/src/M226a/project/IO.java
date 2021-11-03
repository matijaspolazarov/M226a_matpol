package M226a.project;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * IO is the only class which is independent within this project
 */

public class IO {

    static Scanner sc = new Scanner(System.in);

    private static int answer = 0;
    //public static int answer2 = 0;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    /**
     * The function drawbox() draws the boxes in the menu. At first this function prints out "Vending Machine".
     * After that the function prints out a switch case. There are three options the user can choose: "Manager",
     * "Customer", "3. Check Connection". "Check Connection" is to check the connection to the database. If there's an
     * error, the user
     * @return an int to use answer2 in a different function but with the value of the userInput(answer2).
     */
    public int drawbox() {
        System.out.println("\n" +
                " ___ ___                 __ __                   _______              __     __              \n" +
                "|   |   |.-----.-----.--|  |__|.-----.-----.    |   |   |.---.-.----.|  |--.|__|.-----.-----.\n" +
                "|   |   ||  -__|     |  _  |  ||     |  _  |    |       ||  _  |  __||     ||  ||     |  -__|\n" +
                " \\_____/ |_____|__|__|_____|__||__|__|___  |    |__|_|__||___._|____||__|__||__||__|__|_____|\n" +
                "                                     |_____|                                                 ");
        drawBox(30, "1. Manager");
        drawBox(30, "2. Customer ");
        drawBox(30, "3. Check Connection ");

        System.out.println("\n\nPick a number: ");
        int answer2 = IO.readRangedInt(1, 3);

        while (!(answer2 >= 1 && answer2 <= 3)) {

            drawBox(30, "1. Manager");
            drawBox(30, "2. Customer ");
            drawBox(30, "3. Check Connection ");

            System.out.println("\n\nPick a number: ");
            answer2 = IO.readRangedInt(1, 3);


        }
        return answer2;
    }


    /**
     * This function is to read a ranged int. If you want an input of the user between two numbers, this ranged int is
     * usable. For example
     * @param min is to set the minimal number in the ranged Input.
     * @param max is to set the maximal number in the ranged Input.
     * @return The function returns a ranged int between two numbers to use the input in a different method but with the
     * same value.
     */
    public static int readRangedInt(int min, int max) {
        int input = min - 1;
        try {
            input = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException var6) {
            sc.nextLine();
        }
        while (input < min || input > max) {
            System.out.println("There was an Error, please select a valid number");
            try {
                input = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException var5) {
                sc.nextLine();
            }
        }
        return input;
    }

    /**
     * The function readInt() is to read a simple int. If the user inputs the wrong datatype, the program will catch an
     * exception and print out "There was an Error, please repeat your input".
     * @return The function returns an int to use it in a different function with the same value.
     */
    public static int readInt() {
        int input = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                input = Integer.parseInt(sc.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("There was an Error, please repeat your input");
            }
        }

        return input;
    }

    /**
     * The function readString() simply reads a string.
     * @return This function returns a String to use it in different functions with the same value.
     */
    public static String readString() {
        return sc.nextLine();
    }

    /**
     * The function readDouble() reads doubles. This function throws an exception if the user inputs data with the wrong
     * datatype. If the functions throws an exception the program will print out "There was an Error, please repeat your
     * input".
     * @return The function readDouble() returns a double to use the variable "input" in other functions but with the
     * same value.
     */
    public static double readDouble() {
        double input = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                input = Double.parseDouble(sc.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("There was an Error, please repeat your input");
            }
        }
        return input;
    }

    /**
     * This function is the basic structure from the function drawbox(). The function drawbox() is calling the function
     * drawBox() to write a String within a box. The boxes are drawn with unicode.
     *
     * @param length is to define the length of a box.
     * @param singleWord is to define a String within the box.
     */
    public static void drawBox(int length, String singleWord) {

        if (singleWord.length() > length) {
            length = singleWord.length();
        }

        if (length % 2 != 0) {
            length--;
        }

        System.out.print("\u2554");

        for (int i = 0; i < length; i++) {

            System.out.print("\u2550");

        }

        System.out.print("\u2557\n");

        System.out.print("\u2551");

        for (int j = 0; j < (length - singleWord.length()) / 2; j++) {

            System.out.print(" ");
        }

        System.out.print("" + singleWord);

        for (int j = 0; j < (length - singleWord.length()) / 2; j++) {

            System.out.print(" ");

        }

        System.out.print("\u2551\n");

        System.out.print("\u255A");

        for (int i = 0; i < length; i++) {

            System.out.print("\u2550");

        }

        System.out.print("\u255D\n");
    }
}

