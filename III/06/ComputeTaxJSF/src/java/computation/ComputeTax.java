package computation;

import javax.inject.Named;
import java.text.NumberFormat;
import java.util.*;
import javax.enterprise.context.ApplicationScoped;


/**
 * ComputeTax class for computing a tax based on a filing status and a taxable income amount.
 * 
 * Uses a map and a convoluted algorithm implementing the map to determine the tax income.
 * As this was an exercise in creating a JSF page, I kind of winged it on the compute tax
 * method, but it functions like it should.
 */
@Named(value = "computeTax")
@ApplicationScoped
public class ComputeTax {

    // for storing magic numbers corresponding to income caps for filing statuses
    private static Map<String, Integer[]> filingStatusMap = new HashMap<String, Integer[]>() {
        {
            put("Single", new Integer[] {8350, 33950, 82250, 171550, 372950, 372951});
            put("Married - Joint", new Integer[] {16700, 67900, 137050, 208850, 372950, 372951});
            put("Married - Separate", new Integer[] {8350, 33950, 68525, 104425, 186475, 186476});
            put("Head of Household", new Integer[] {11950, 45500, 117450, 190200, 372950, 372951});
        }
    };

    // for formatting currency display
    private Locale locale = Locale.getDefault();
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

    // for storing input information
    private double taxableIncome;
    private String filingStatus;
    private double taxAmount;


    public ComputeTax() {
    }

    /**
     * Computes the taxAmount using a modified version of the ComputeTax class
     * from the textbook.
     *
     * This is a marginal improvement over the example given in the book.
     * Instead of a bunch of magic numbers and ton of multi-nested if statements,
     * it's a little more simplified by using the magic numbers in an array and
     * retrieving the correct magic numbers to use for the calculations from
     * the map of filing statuses corresponding to the magic numbers.
     *
     * This is still really terrible code but it works (surprisingly).
     *
     * Todo: Implement a better algorithm.
     */
    public void computeTaxAmount() {
        // get the tax ranges
        Integer[] taxRanges = filingStatusMap.get(filingStatus);

        if (taxableIncome <= taxRanges[0]) {
            taxAmount = taxableIncome * 0.10;
        }
        else if (taxableIncome <= taxRanges[1]) {
            taxAmount = taxRanges[0] * 0.10 + (taxableIncome - taxRanges[0]) * 0.15;
        }
        else if (taxableIncome <= taxRanges[2]) {
            taxAmount = taxRanges[0] * 0.10 + (taxRanges[1] - taxRanges[0]) * 0.15 +
                      (taxableIncome - taxRanges[1]) * 0.25;
        }
        else if (taxableIncome <= taxRanges[3]) {
            taxAmount = taxRanges[0] * 0.10 + (taxRanges[1] - taxRanges[0]) * 0.15 +
                      (taxRanges[2] - taxRanges[1]) * 0.25 + (taxableIncome - taxRanges[2]) * 0.28;
        }
        else if (taxableIncome <= taxRanges[4]) {
            taxAmount = taxRanges[0] * 0.10 + (taxRanges[1] - taxRanges[0]) * 0.15 +
                      (taxRanges[2] - taxRanges[1]) * 0.25 + (taxRanges[3] - taxRanges[2]) * 0.28 +
                      (taxableIncome - taxRanges[3]) * 0.33;
        }
        else {
            taxAmount = taxRanges[0] * 0.10 + (taxRanges[1] - taxRanges[0]) * 0.15 +
                      (taxRanges[2] - taxRanges[1]) * 0.25 + (taxRanges[3] - taxRanges[2]) * 0.28 +
                      (taxRanges[4] - taxRanges[3]) * 0.33 + (taxableIncome - taxRanges[4]) * 0.35;
        }
    }

    /**Returns currency formatted string of taxableIncome.*/
    public String getTaxableIncomeString() {
        return currencyFormat.format(taxableIncome);
    }

    /**Setter for taxableIncome*/
    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }
    
    /**Getter for taxableIncome*/
    public double getTaxableIncome() {
        return taxableIncome;
    }

    /**
     * Gets a String array of possible filing statuses listed in the filingStatusMap
     * @return possible filing status strings
     */
    public String[] getFilingStatusStringArray() {
        Set<String> set = filingStatusMap.keySet();
        String[] arr = new String[set.size()];
        set.toArray(arr);
        return arr;
    }

    /**Getter for filingStatus*/
    public String getFilingStatus() {
        return filingStatus;
    }

    /**Setter for filingStatus*/
    public void setFilingStatus(String filingStatus) {
        this.filingStatus = filingStatus;
    }

    /**Computes and updates taxAmount before returning it*/
    public double getTaxAmount() {
        computeTaxAmount();
        return taxAmount;
    }

    /**Returns currency formatted string of taxAmount.*/
    public String getTaxAmountString() {
        return currencyFormat.format(getTaxAmount());
    }
    
    /**Returns html string for response*/
    public String getResponse() {
        if (filingStatus == null || taxAmount == 0 || taxableIncome == 0) {
            return "";
        }
        return "<p>Taxable Income: " + getTaxableIncomeString() + "<br />" +
                "Filing Status: " + getFilingStatus() + "<br />" +
                "Tax: " + getTaxAmountString() + "</p>";
    }
}
