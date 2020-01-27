package bankaccountandsavingsaccount;

/**
 * @author Stephen Gerkin
 * @date 2019-06-04
 * Programming Lab 05 - 9. BankAccount and SavingsAccount Classes
 * Program to demonstrate abstract classes and inheritance with a bank account
 */
public class Main {

    /**
     * Driver to demo SavingsAccount class
     * @param args command line args
     */
    public static void main(String[] args) {
        SavingsAccount demo = new SavingsAccount(100.00, .05);

        print("Initialized Account" + "\n" + demo.toString());

        print("make some deposits");
        for (int i = 0; i < 5; i++) {
            demo.deposit(20);
        }
        print(demo.toString());

        print("make a big withdrawal");
        demo.withdraw(150);
        print(demo.toString());

        print("make a lot of small withdrawals");
        for (int i = 0; i < 25; i++) {
            demo.withdraw(1);
        }
        print(demo.toString());

        print("make account inactive");
        demo.withdraw(.01);
        print(demo.toString());

        print("demonstrate inability to withdraw when inactive");
        demo.withdraw(20);

        print("show monthly process\n"
            + "should subtract $22 (balance of $2.00)\n"
            + "then add 5% annual interest (which is $0)"
        );
        demo.monthlyProcess();

        print("display $2.00 as balance\n"
            + "shows all applicable fields reset to 0\n"
            + demo.toString()
        );

        print("sell a kidney to raise account back into the black");
        demo.deposit(150_000);
        print(demo.toString());

        print("demonstrate interest working as intended\n"
            + "will add (.05/12) * 150,002.00 = $625.01"
        );
        demo.monthlyProcess();
        print(demo.toString());

        System.exit(0);
    }

    /**
     * Helper class to print stuff to the screen
     * @param str
     */
    public static void print(String str) {
        System.out.println(str);
        System.out.println();
    }
}
