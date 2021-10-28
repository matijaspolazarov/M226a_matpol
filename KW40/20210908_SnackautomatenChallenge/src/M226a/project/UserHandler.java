package M226a.project;

public class UserHandler {

    IO io;
    CustomerHandler ch;
    ManagerHandler mh;
    SnackImporter si;
    PrintSnackMachine ps;

    public UserHandler() {
        io = new IO();
        mh = new ManagerHandler(this);
        ch = new CustomerHandler(mh); //different objects
        si = new SnackImporter();
        ps = new PrintSnackMachine();

        mh.setSnacks(si.grabData("SELECT * FROM user.snack;"));
        welcomeMessage();
    }

    public void welcomeMessage() {
        while (true) {
            switch (io.drawbox()) {
                case 1 -> snackManager();
                case 2 -> customerManager();
                case 3 -> si.testConnection();
            }
        }
    }

    public void snackManager() {
        System.out.println("What's the password? ");
        mh.setPasswordAnswer(IO.readInt());

        while (mh.getPasswordAnswer() != mh.getPassword()) {
            System.out.println("Wrong password, please try again");
            mh.setPasswordAnswer(IO.readInt());
        }
        int tempAnswer = 0;
        while (tempAnswer != 7) {
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
            tempAnswer = IO.readRangedInt(1, 8);

            switch (tempAnswer) {
                case 1 -> mh.addSnack();
                case 2 -> mh.deleteSnack();
                case 3 -> mh.editPrice();
                case 4 -> mh.changePin();
                case 5 -> mh.changeUser();
                case 6 -> mh.fillAllSnacks();
                case 8 -> System.exit(0);
            }
        }
    }

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