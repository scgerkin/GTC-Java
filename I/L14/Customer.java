/*
    Customer class definition
 */
package lesson14lab12;

import java.net.SocketAddress;

/**
 * This class inherits the Person class and adds customer number and a boolean
 * value for mailing list status
 */
public class Customer extends Person {

    // instance variables
    private String customerNumber;
    private Boolean mailingList;    // true for on mailing list

    /**
     * Default no-arg constructor
     */
    public Customer() {
        super();
        customerNumber = "";
        mailingList = false;
    }

    /**
     * Constructor with arguments
     * @param n     name
     * @param a     address
     * @param p     phone number
     * @param c     customer number
     * @param m     mailing list status (true for on mailing list)
     */
    public Customer(String n, String a, String p, String c, Boolean m){
        super(n, a, p);
        customerNumber = c;
        mailingList = m;
    }

    /**
     * Mutator for customer number
     * @param c     customer number
     */
    public void setCustomerNumber(String c) {
        customerNumber = c;
    }

    /**
     * Mutator for mailing list status
     * @param m     boolean for mailing list
     */
    public void setMailingList(Boolean m) {
        mailingList = m;
    }

    /**
     * Getter for customer number
     * @return
     */
    public String getCustomerNumber() {
        return customerNumber;
    }

    /**
     * Getter for mailing list status
     * @return
     */
    public Boolean getMailingList() {
        return mailingList;
    }
}
