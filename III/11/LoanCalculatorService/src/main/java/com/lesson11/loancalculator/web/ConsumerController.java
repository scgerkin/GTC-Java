package com.lesson11.loancalculator.web;

import com.lesson11.loancalculator.command.FormCommand;
import com.lesson11.loancalculator.entity.Loan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a client consumer of the REST API that the LoanServiceController
 * creates a service for. It'd be simpler (and better practice) to use a Command
 * to pass the information around between controllers and views, but the project
 * asks for a client to consume the service. This was the simplest method I could
 * think of without writing an entirely different program.
 */
@Controller
public class ConsumerController {

    private final String endpoint = "http://localhost:8080/";

    /**
     * Sets up the index page when we go to the server. It gives the Model a
     * Command to work with for getting information on the form then tells
     * Spring we want to go to the index template.
     */
    @RequestMapping(value="/")
    public String initIndexView(Model model) {
        model.addAttribute("formCommand", new FormCommand());
        return "index";
    }

    /**
     * This handles POST requests from the index template (or anywhere that tries
     * to post to the server without a subdirectory). It takes the Command with
     * the form information, gets the values from it, then consumes our REST
     * service. It then puts all of this back into the Command to give back to
     * the Model for Thymeleaf to render.
     */
    @PostMapping(value="/")
    public String postIndexResult(@ModelAttribute("formCommand") FormCommand formCommand,
                                  Model model) {
        // unpack the loan information we took from the model
        Double loanAmount = formCommand.getAmt();
        Integer numYears = formCommand.getYrs();
        Double interestRate = formCommand.getInterestRate();
        String paymentType = formCommand.getPaymentType();
        Boolean monthly = paymentType.equals("Monthly");

        // construct the REST resource and the URI
        final String resource = ((monthly)?"monthly":"total") +
                                    "-payment/" + loanAmount + "/" + numYears + "/" + interestRate;
        final String uri = endpoint + resource;

        // consume the REST service
        RestTemplate restTemplate = new RestTemplate();
        Loan loan = restTemplate.getForObject(uri, Loan.class);

        // box the result back into the command and hand it back to the model
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        try {
            formCommand.setResult(nf.format(loan.getResult()));
        }
        catch (NullPointerException ex) {
            formCommand.setResult("ERROR");
        }

        // give the model back what we took and tell Spring to go to the index
        // template again
        model.addAttribute("formCommand", formCommand);
        return "index";
    }

    /**
     * This is a Spring/Thymeleaf specific getter method. Whenever Thymeleaf
     * requests "radioSelection" from the Model, it will be given the return
     * value of this method.
     * This is used here to set our radio button values for selecting what type
     * of information we want to receive back from the REST service.
     */
    @ModelAttribute("radioSelection")
    public List<String> getRadioSelections() {
        List<String> list = new ArrayList<>();
        list.add("Monthly");
        list.add("Total");
        return list;
    }
}
