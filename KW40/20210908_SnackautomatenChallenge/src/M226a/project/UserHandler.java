package M226a.project;

/**
 * This class handles the users Manager and Customer. It handles the menus of the Customer and of the Manager.
 * The functions are written in the separate classes ManagerHandler and CustomerHandler. In this class the functions are
 * called.
 */
public class UserHandler {

    private IO io;
    private CustomerHandler ch;
    private ManagerHandler mh;
    private SnackImporter si;
    private PrintSnackMachine ps;

    public UserHandler() {
        io = new IO();
        mh = new ManagerHandler(this);
        ch = new CustomerHandler(mh); //different objects
        si = new SnackImporter();
        ps = new PrintSnackMachine();

        mh.setSnacks(si.grabData("SELECT * FROM user.snack;"));
        welcomeMessage();
    }

    /**
     * This function (welcomeMessage()) prints out the boxes and handles the user input within a switch-case
     */
    private void welcomeMessage() {
        while (true) {
            switch (io.drawbox()) {
                case 1 -> snackManager();
                case 2 -> customerManager();
                case 3 -> si.testConnection();
            }
        }
    }

    /**
     * The function snackManager() handles the Manager. If the user decides to pick the manager he first has to write in
     * the manager's password. If the password is correct he gets the Manager menu. The manager menu is also solved within
     * a switch-case. The Manager can add a snack, delete a snack, edit the price of a snack, change the pin, change user
     * and end the program.
     */
    public void snackManager() {
        int wrongPw = 3;
        String answer = "";
        System.out.println("What's the password? ");
        mh.setPasswordAnswer(IO.readInt());

        while (mh.getPasswordAnswer() != mh.getPin()) {
            System.out.println("Wrong password, please try again (" + wrongPw + " Attempts remaining)");
            mh.setPasswordAnswer(IO.readInt());
            //wrongPw--;
            /*if (wrongPw <= 0) {
                System.out.println("No more attempts");

                while (!(answer.equals("A1B2C3"))) {
                    System.out.println("Type in 'A1B2C3'");
                    answer = IO.readString();
                }
            } else if (answer.equals("A1B2C3")) {
                wrongPw = 3;
                snackManager();
            }*/
        }
        int tempAnswer = 0;
        IO.drawBox(25, "Hello Manager ");
        while (tempAnswer != 7) {
            System.out.println("");
            IO.drawBox(25, "1. add snack");
            IO.drawBox(25, "2. delete Snack ");
            IO.drawBox(25, "3. edit Price ");
            IO.drawBox(25, "4. change Pin ");
            IO.drawBox(25, "5. change user");
            IO.drawBox(25, "8. End program");

            System.out.println("Please select something");
            tempAnswer = IO.readRangedInt(1, 8);

            switch (tempAnswer) {
                case 1 -> mh.addSnack();
                case 2 -> mh.deleteSnack();
                case 3 -> mh.editPrice();
                case 4 -> mh.changePin();
                case 5 -> mh.changeUser();
                case 8 -> System.exit(0);
            }
        }
    }

    /**
     * This function handles the Customer. It's solved within a switch-case. The user can input a number and then the
     * function
     */
    public void customerManager() {
        int tempAnswer = 0;
        while (tempAnswer != 8) {
            IO.drawBox(25, "1. Insert Coins ");
            IO.drawBox(25, "2. buy snacks ");
            IO.drawBox(25, "3. print current balance");
            IO.drawBox(25, "4. change user");
            IO.drawBox(25, "5. print Snacks ");
            IO.drawBox(25, "8. end Program");

            System.out.println("\n\nPick a number: ");
            tempAnswer = IO.readRangedInt(1, 8);

            switch (tempAnswer) {
                case 1 -> ch.insertCoins();
                case 2 -> ch.buySnack();
                case 3 -> ch.printUserBalance();
                case 4 -> ch.changeUser();
                case 5 -> ps.printSnacks(mh.getSnacks(), 3, 25);
                case 8 -> System.exit(0);
            }
        }
    }
}