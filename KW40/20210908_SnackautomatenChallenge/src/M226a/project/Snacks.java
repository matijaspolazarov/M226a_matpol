package M226a.project;

public class Snacks {
    private String snackName;
    private double snackPrice;
    private Long numberOfSnacks;

    public Snacks() {
    }

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
