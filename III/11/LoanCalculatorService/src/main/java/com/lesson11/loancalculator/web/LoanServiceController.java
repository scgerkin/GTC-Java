package com.lesson11.loancalculator.web;

import com.lesson11.loancalculator.entity.Loan;
import com.lesson11.loancalculator.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling requests to calculate loan payments.
 * The @RestController annotation tells Spring that this class handles REST
 * service requests and is allowed to respond with a ResponseEntity container.
 *
 * To illustrate handling POJO items, this controller will return a Loan object
 * to the consumer.
 */
@RestController
public class LoanServiceController {
    private LoanService loanService;

    /**
     * Setter for the LoanService implementation we will use.
     * The annotation tells Spring that this should be automatically set when the
     * application is launched. It looks for a class that implements the LoanService
     * interface and has the @Service annotation, then instantiates an object of
     * that type to hand over to the controller.
     */
    @Autowired
    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * Controller method mapping for requests to calculate the monthly minimum
     * payment of a loan.
     *
     * The @GetMapping annotation tells Spring what to look for when submitting
     * requests to our server. It allows for both POST and GET methods.
     * The @PathVariables grab the values passed in the URL and assign them to
     * Java objects we can use. It then returns a Spring ResponseEntity container
     * that contains both the response we want to send and an HTTP Status code.
     *
     * @param amt The starting amount of a loan.
     * @param yrs The number of years to pay off a loan.
     * @param interest The interest rate for the loan.
     * @return The minimum monthly payment required to pay off the loan in time.
     */
    @GetMapping(value="monthly-payment/{amt}/{yrs}/{interest}")
    public ResponseEntity<Loan> getMonthlyPayment(@PathVariable Double amt,
                                                    @PathVariable Integer yrs,
                                                    @PathVariable Double interest) {
        Double payment = loanService.monthlyPayment(amt, yrs, interest);
        Loan loan = new Loan(amt, yrs, interest, payment);
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    /**
     * Controller method mapping for requests to calculate the total payment for
     * a loan given each payment is the minimum payment allowed.
     * @param amt The starting amount of a loan.
     * @param yrs The number of years to pay off a loan.
     * @param interest The interest rate for the loan.
     * @return The total payment amount required to pay off the loan given each
     *         payment is the minimum.
     */
    @GetMapping(value="total-payment/{amt}/{yrs}/{interest}")
    public ResponseEntity<Loan> getTotalPayment(@PathVariable Double amt,
                                                  @PathVariable Integer yrs,
                                                  @PathVariable Double interest) {
        Double payment = loanService.totalPayment(amt, yrs, interest);
        Loan loan = new Loan(amt, yrs, interest, payment);
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }
}
