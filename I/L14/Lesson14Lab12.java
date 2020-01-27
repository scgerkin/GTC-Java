/**
 * @author Stephen Gerkin
 * Date: 04/16/2019
 * Lesson 14 Lab 12
 * Program Name:
 *  Person, Customer, and PreferredCustomer Classes
 * Program Description:
 *  Program creates and demonstrates inheritance through creating objects
 *  that inherit others.
 */
package lesson14lab12;

import java.text.DecimalFormat;

public class Lesson14Lab12 {

    // var for output messages
    public static String outMsg;

    // decimal format objects
    public static DecimalFormat percent = new DecimalFormat("##0.##%");
    public static DecimalFormat dollar = new DecimalFormat("$###,##0.00");

    /**
     * Main method declaration
     */
    public static void main(String[] args) {


        // construct PreferredCustomer object with no arguments
        PreferredCustomer blankDemo = new PreferredCustomer();

        // construct PreferredCustomer object with all arguments
        PreferredCustomer fullDemo = new PreferredCustomer("Smith, John",
            "123 Street Rd.", "555-555-5555", "9876", true, 499);

        // All should print empty strings
        outMsg = "Name: " + blankDemo.getName() + "\n"
            + "Address: " + blankDemo.getAddress() + "\n"
            + "Phone: " + blankDemo.getPhone() + "\n"
            + "Customer Number: " + blankDemo.getCustomerNumber() + "\n"
            + "Mailing List: " + blankDemo.getMailingList() + "\n"
            + "Amount Purchased:"
            + dollar.format(blankDemo.getAmountPurchased()) + "\n"
            + "Discount: " + percent.format(blankDemo.getDiscount()) + "\n";
        System.out.println(outMsg);

        // change values with mutators and display updated information
        blankDemo.setName("Johnson, Jane");
        blankDemo.setAddress("123 Road St.");
        blankDemo.setPhone("123-456-7890");
        blankDemo.setCustomerNumber("54321");
        blankDemo.setMailingList(true);
        blankDemo.setAmountPurchased(750);

        // demonstrate mutators updated information correctly
        outMsg = "Name: " + blankDemo.getName() + "\n"
            + "Address: " + blankDemo.getAddress() + "\n"
            + "Phone: " + blankDemo.getPhone() + "\n"
            + "Customer Number: " + blankDemo.getCustomerNumber() + "\n"
            + "Mailing List: " + blankDemo.getMailingList() + "\n"
            + "Amount Purchased:"
            + dollar.format(blankDemo.getAmountPurchased()) + "\n"
            + "Discount: " + percent.format(blankDemo.getDiscount()) + "\n";
        System.out.println(outMsg);


        // All should print values used in constructor and 0% discount
        outMsg = "Name: " + fullDemo.getName() + "\n"
            + "Address: " + fullDemo.getAddress() + "\n"
            + "Phone: " + fullDemo.getPhone() + "\n"
            + "Customer Number: " + fullDemo.getCustomerNumber() + "\n"
            + "Mailing List: " + fullDemo.getMailingList() + "\n"
            + "Amount Purchased:"
            + dollar.format(fullDemo.getAmountPurchased()) + "\n"
            + "Discount: " + percent.format(fullDemo.getDiscount()) + "\n";
        System.out.println(outMsg);

        // Increase amount purchased and show updated amount
        // and discount of 5%
        fullDemo.addPurchase(5);
        outMsg = "Amount Purchased:"
            + dollar.format(fullDemo.getAmountPurchased()) + "\n"
            + "Discount: " + percent.format(fullDemo.getDiscount()) + "\n";
        System.out.println(outMsg);

        // Use setPurchased method and show updated amount and discount of 6%
        fullDemo.setAmountPurchased(1001);
        outMsg = "Amount Purchased:"
            + dollar.format(fullDemo.getAmountPurchased()) + "\n"
            + "Discount: " + percent.format(fullDemo.getDiscount()) + "\n";
        System.out.println(outMsg);

        // Increment amount purchased again and display discount of 7%
        fullDemo.addPurchase(500);
        outMsg = "Amount Purchased:"
            + dollar.format(fullDemo.getAmountPurchased()) + "\n"
            + "Discount: " + percent.format(fullDemo.getDiscount()) + "\n";
        System.out.println(outMsg);

        // Show final decision works with amount equal to 2000
        fullDemo.setAmountPurchased(2000);
        outMsg = "Amount Purchased:"
            + dollar.format(fullDemo.getAmountPurchased()) + "\n"
            + "Discount: " + percent.format(fullDemo.getDiscount()) + "\n";
        System.out.println(outMsg);

        // terminate JVM
        System.exit(0);
    }

}
