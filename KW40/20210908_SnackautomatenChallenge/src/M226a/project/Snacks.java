package M226a.project;

public class Snacks {
    private String snackName;
    private double snackPrice;
    private Long numberOfSnacks;

    /**
     * This Constructor is to create Snacks for the snackMachine with the attributes snackName, snackPrice, and
     * numberOfSnack.
     *
     * @param snackName      is to call the Snack by a name. With this name you can later change several things (edit price,
     *                       delete Snack, etc.)
     * @param snackPrice     is for the user to buy a snack. The user got a function to insert coins. With this coins
     *                       he can buy a snack, if he has enough coins.
     * @param numberOfSnacks is to set a number on how many snacks there are of a snack.
     */

    public Snacks(String snackName, double snackPrice, Long numberOfSnacks) {
        this.snackName = snackName;
        this.snackPrice = snackPrice;
        this.numberOfSnacks = numberOfSnacks;
    }

    public String getSnackName() {
        return snackName;
    }

    public void setSnackName(String snackName) {
        this.snackName = snackName;
    }

    public double getSnackPrice() {
        return snackPrice;
    }

    public void setSnackPrice(double snackPrice) {
        this.snackPrice = snackPrice;
    }

    public Long getNumberOfSnacks() {
        return numberOfSnacks;
    }

    public void setNumberOfSnacks(Long numberOfSnacks) {
        this.numberOfSnacks = numberOfSnacks;
    }
}
