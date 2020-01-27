package passswordverifier;

/**
 * @author Stephen Gerkin
 * @date 2019-05-24
 * Programming Lab 4 - 5. Password Verifier
 * Program to get and verify passwords from a user
 */
public class Main {

    /**
     * Main method demonstrates the Password utility class
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Getting Weak Password...");
        String pwWeak = Password.getVerifiedWeak();
        System.out.println();

        System.out.println("Getting Medium Password...");
        String pwMedium = Password.getVerifiedMedium();
        System.out.println();

        System.out.println("Getting Strong Password...");
        String pwStrong = Password.getVerifiedStrong();
        System.out.println();

        System.exit(0);
    }
}
