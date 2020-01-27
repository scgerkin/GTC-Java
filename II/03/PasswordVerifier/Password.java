package passswordverifier;

import java.util.regex.Pattern;
import java.util.Scanner;

/**
 * Password utility class provides methods for getting passwords of varying
 * degrees of strength from a user and verifying the strength of the input
 */
public final class Password {

    // class scanner object for keyboard input
    private static final Scanner kb = new Scanner(System.in);

    // regex string patterns for valid passwords
    private static final String weakPattern =
        "^(?=.*\\d)(?=.*[a-zA-Z]).{4,}$";
    private static final String mediumPattern =
        "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$";
    private static final String strongPattern =
        "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*)(]).*.{10,}$";


    /**
     * Method for getting and verifying weak passwords
     * @return verified weak user password
     */
    public static final String getVerifiedWeak() {
        String input = getWeak();

        while (!verifyWeak(input)) {
            printInvalid();
            input = getWeak();
        }

        return input;
    }

    /**
     * Method for getting and verifying medium passwords
     * @return verified medium user password
     */
    public static final String getVerifiedMedium() {
        String input = getMedium();

        while (!verifyMedium(input)) {
            printInvalid();
            input = getMedium();
        }

        return input;
    }

    /**
     * Method for getting and verifying strong passwords
     * @return verified strong user password
     */
    public static final String getVerifiedStrong() {
        String input = getStrong();

        while (!verifyStrong(input)) {
            printInvalid();
            input = getStrong();
        }

        return input;
    }

    /**
     * Method for getting a password with weak criteria stated
     * DOES NOT VERIFY INPUT
     * Only use this method if you want to create your own handling of invalid
     * password strengths
     * @return user input
     */
    public static final String getWeak() {
        System.out.print(
            "Valid password must:\n"
            + "1. Be at least 4 characters long\n"
            + "2. Contain at least 1 digit\n"
            + "\n"
            + "Please enter a password: "
        );

        return kb.nextLine();
    }

    /**
     * Method for getting a password with medium criteria stated
     * DOES NOT VERIFY INPUT
     * Only use this method if you want to create your own handling of invalid
     * password strengths
     * @return user input
     */
    public static final String getMedium() {
        System.out.print(
            "Valid password must:\n"
            + "1. Be at least 6 characters long\n"
            + "2. Contain at least 1 Uppercase letter\n"
            + "3. Contain at least 1 lowercase letter\n"
            + "4. Contain at least 1 digit\n"
            + "\n"
            + "Please enter a password: "
        );

        return kb.nextLine();
    }

    /**
     * Method for getting a password with strong criteria stated
     * DOES NOT VERIFY INPUT
     * Only use this method if you want to create your own handling of invalid
     * password strengths
     * @return user input
     */
    public static final String getStrong() {
        System.out.print(
            "Valid password must:\n"
            + "1. Be at least 10 characters long\n"
            + "2. Contain at least 1 Uppercase letter\n"
            + "3. Contain at least 1 lowercase letter\n"
            + "4. Contain at least 1 digit\n"
            + "5. Contain at least 1 special character: !@#$%^&*)("
            + "\n"
            + "Please enter a password: "
        );

        return kb.nextLine();
    }

    /**
     * Method for verifying weak password
     * @param input
     * @return
     */
    public static Boolean verifyWeak(String input) {
        return Pattern.matches(weakPattern, input);
    }

    /**
     * Method for verifying medium password
     * @param input
     * @return
     */
    public static Boolean verifyMedium(String input) {
        return Pattern.matches(mediumPattern, input);
    }

    /**
     * Method for verifying strong password
     * @param input
     * @return
     */
    public static Boolean verifyStrong(String input) {
        return Pattern.matches(strongPattern, input);
    }

    /**
     * Helper method to display invalid password to user
     */
    private static final void printInvalid() {
        System.out.println("That password is not strong enough.\n");
    }
}
