LoanService Assignment

You can compute a loan payment for a loan with the specified amount, number of years, and annual interest rate.  Write a web service called LoanService with two remote methods for computing monthly payment and total payment.  Name these methods monthlyPayment and totalPayment respectfully.

Write a JSF (JavaServer Faces) client application that consumes the LoanService web service.  The web client application should have a web page with a form allowing input of loan amount, number of years, and annual interest rate.  The form should include validating the entered values.  On clicking the Calculate button on the form the application will use the web service to get the monthly and total payments and display them on a web page.

Additional Notes:

    For validation purposes all three input fields are required and must be numeric,  the loan amount must be at least $500.00, the interest rate must be greater than 0% and less than 100%, and the number of years must be a minimum of 1 year and a maximum of 100 years..

