package com.company;

import java.io.File;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

//IO ist unabhängig von allen Klassen (als einzige Klasse)

public class IO {

    static Scanner sc = new Scanner(System.in);

    public static int answer = 0;
    public static int answer2 = 0;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

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
        answer2 = IO.readInt();
        while (answer2 != 1 && answer2 != 2 && answer2 != 3 && answer2 != 4) {

            drawBox(30, "1. Manager");
            drawBox(30, "2. Customer ");
            drawBox(30, "3. Check Connection ");
            drawBox(30, "4. Grab Data");

            System.out.println("\n\nPick a number: ");
            answer2 = IO.readInt();


        }
        return answer2;
    }

    public static int readRangedInt(int min, int max) {
        int input = min - 1;
        try {
            input = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException var6) {
            sc.nextLine();
        }
        while (input < min || input > max) {
            System.out.println("There was an Error, please repeat your input");
            try {
                input = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException var5) {
                sc.nextLine();
            }
        }
        return input;
    }

    public static int readInt() {
        int input = 0;
        boolean isValid = false;
        try {
            input = Integer.parseInt(sc.nextLine());
            isValid = true;
        } catch (NumberFormatException e) {
        }
        while (!isValid) {
            try {
                input = Integer.parseInt(sc.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
            }
        }

        return input;
    }

    public static String readString() {
        return sc.nextLine();
    }


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

