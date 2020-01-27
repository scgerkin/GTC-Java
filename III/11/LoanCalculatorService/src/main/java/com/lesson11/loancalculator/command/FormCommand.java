package com.lesson11.loancalculator.command;

/**
 * Data transfer object for getting information from the form on the index page.
 * Spring refers to these as "Commands" instead of DTO.
 */
public class FormCommand {
    Double amt;
    Integer yrs;
    Double interestRate;
    String paymentType;
    String result;

    public FormCommand() {
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

    public Integer getYrs() {
        return yrs;
    }

    public void setYrs(Integer yrs) {
        this.yrs = yrs;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
