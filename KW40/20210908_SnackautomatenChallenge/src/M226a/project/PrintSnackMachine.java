package M226a.project;

import java.util.ArrayList;

/**
 *
 */
public class PrintSnackMachine {
    private final String HOR_LINE = "═";
    private final String VER_LINE = "║";
    private final String CROSS = "╬";
    private final String VER_LINE_LEFT = "╠";
    private final String VER_LINE_RIGHT = "╣";
    private final String HOR_LINE_TOP = "╦";
    private final String HOR_LINE_BOTTOM = "╩";
    private final String TOP_LEFT_CORNER = "╔";
    private final String TOP_RIGHT_CORNER = "╗";
    private final String BOTTOM_LEFT_CORNER = "╚";
    private final String BOTTOM_RIGHT_CORNER = "╝";

    public void printSnacks(ArrayList<Snacks> snacks, int snacksPerRow, int slotWidth) {
        if (snacksPerRow < 1) snacksPerRow = 1;
        int length = stringLength(slotWidth, snacks);
        int amountRows = snacks.size() / snacksPerRow - (snacks.size() % snacksPerRow > 0 ? 0 : 1);

        topRow(length, snacksPerRow);
        for (int i = 0; i <= amountRows; i++) {
            //Content
            snackName(length, snacksPerRow, i, snacks);
            snackPrice(length, snacksPerRow, i, snacks);
            snackNumber(length, snacksPerRow, i, snacks);

            if (i == amountRows) break;
            midRow(length, snacksPerRow);
        }
        lastRow(length, snacksPerRow);
        System.out.println("\n");
    }

    public void topRow(int length, int snacksPerRow) {
        row(length, snacksPerRow, TOP_LEFT_CORNER, TOP_RIGHT_CORNER, HOR_LINE_TOP);
    }

    public void midRow(int length, int snacksPerRow) {
        row(length, snacksPerRow, VER_LINE_LEFT, VER_LINE_RIGHT, CROSS);
    }

    private void row(int length, int snacksPerRow, String left, String right, String middle) {
        for (int i = 0; i <= snacksPerRow; i++) {
            if (i == 0) {
                System.out.print(left);
            } else if (i == snacksPerRow) {
                System.out.println(right);
                break;
            } else {
                System.out.print(middle);
            }
            for (int k = 0; k < length; k++) {
                System.out.print(HOR_LINE);
            }
        }
    }

    public void lastRow(int length, int snacksPerRow) {
        row(length, snacksPerRow, BOTTOM_LEFT_CORNER, BOTTOM_RIGHT_CORNER, HOR_LINE_BOTTOM);
    }

    public int stringLength(int slotWidth, ArrayList<Snacks> snacks) {
        for (int i = 0; i < snacks.size(); i++) {
            String price = "Price: " + String.format("%04.2f", snacks.get(i).getSnackPrice());
            String amount = "Nr. " + String.format("%03d", i) + " (" +
                    String.format("%03d", snacks.get(i).getNumberOfSnacks()) + ")";

            slotWidth = Math.max(slotWidth, snacks.get(i).getSnackName().length());
            slotWidth = Math.max(slotWidth, price.length());
            slotWidth = Math.max(slotWidth, amount.length());
        }

        return slotWidth + 2;
    }

    public void printContent(int length, String content) {
        for (int i = 0; i < spaceDistance(length, content.length())[0]; i++) {
            System.out.print(" ");
        }
        System.out.print(content);
        for (int i = 0; i < spaceDistance(length, content.length())[1]; i++) {
            System.out.print(" ");
        }
    }

    public void snackName(int length, int snacksPerRow, int i, ArrayList<Snacks> snacks) {
        System.out.print(VER_LINE);
        for (int j = 0; j < snacksPerRow; j++) {
            String content = j + i * snacksPerRow < snacks.size() ? snacks.get(j + i * snacksPerRow).getSnackName() : "";
            printContent(length, content);
            System.out.print(VER_LINE);
        }
        System.out.println();
    }

    public void snackPrice(int length, int snacksPerRow, int i, ArrayList<Snacks> snacks) {
        System.out.print(VER_LINE);
        for (int j = 0; j < snacksPerRow; j++) {
            String content = j + i * snacksPerRow < snacks.size() ? "Price: " +
                    String.format("%04.2f", snacks.get(j + i * snacksPerRow).getSnackPrice()) : "";
            printContent(length, content);
            System.out.print(VER_LINE);
        }
        System.out.println();
    }

    public void snackNumber(int length, int snacksPerRow, int i, ArrayList<Snacks> snacks) {
        System.out.print(VER_LINE);
        for (int j = 0; j < snacksPerRow; j++) {
            String content = j + i * snacksPerRow < snacks.size() ? "Nr. " + String.format("%03d", (j + i * snacksPerRow + 1))
                    + " (" + String.format("%03d", snacks.get(j + i * snacksPerRow).getNumberOfSnacks()) + ")" : "";
            printContent(length, content);
            System.out.print(VER_LINE);
        }
        System.out.println();
    }

    public int[] spaceDistance(int maxLength, int informationLength) {
        int[] spaceGap = new int[2];
        spaceGap[0] = (maxLength - informationLength) / 2;

        if ((maxLength - informationLength) % 2 == 1) {
            spaceGap[1] = spaceGap[0] + 1;
        } else {
            spaceGap[1] = spaceGap[0];
        }
        return spaceGap;
    }
}
