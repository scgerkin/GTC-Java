/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lesson14lab12;

/**
 * This class inherits the Customer class and adds amount purchased
 */
public class PreferredCustomer extends Customer {

    // instance variable
    private double amountPurchased;

    /**
     * Default no-arg constructor
     */
    public PreferredCustomer() {
        super();
        amountPurchased = 0.0;
    }

    /**
     * Constructor with arguments
     * @param n     name
     * @param a     address
     * @param p     phone number
     * @param c     customer number
     * @param m     mailing list status (true for on mailing list)
     * @param ap    amount purchased by customer
     */
    public PreferredCustomer(String n, String a, String p, String c, Boolean m, double ap) {
        super(n, a, p, c, m);
        amountPurchased = ap;
    }

    /**
     * Mutator for amount purchased
     * @param p     amount purchased
     */
    public void setAmountPurchased(double p) {
        amountPurchased = p;
    }

    /**
     * Mutator for incrementing amount purchased
     * @param p     amount to add
     */
    public void addPurchase(double p) {
        amountPurchased += p;
    }

    /**
     * Getter for amount purchased
     * @return
     */
    public double getAmountPurchased() {
        return amountPurchased;
    }

    /**
     * Getter for discount rate
     * Function determines discount and returns as decimal value
     * @return
     */
    public double getDiscount() {
        if (amountPurchased >= 2000) {
            return 0.10;
        }
        if (amountPurchased >= 1500) {
            return 0.07;
        }
        if (amountPurchased >= 1000) {
            return 0.06;
        }
        if (amountPurchased >= 500) {
            return 0.05;
        }

        // if function gets here, discount is 0%
        return 0.00;
    }
}
