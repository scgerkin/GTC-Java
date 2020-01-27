
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.LoanCalculator;

@WebServlet(urlPatterns = {"/CalculateLoan"})
public class CalculateLoan extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        double loanAmount = 0;
        double interestRate = 0;
        int numYears = 0;
        
        try {
            loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
            interestRate = Double.parseDouble(request.getParameter("interestRate"));
            numYears = Integer.parseInt(request.getParameter("numYears"));
        }
        catch (NumberFormatException ex) {
            // shouldn't happen with form only allowing numerical input
            try (PrintWriter out = response.getWriter()) {
                out.print("Invalid input.");
                out.print(ex.getStackTrace().toString());
            }
        }
        
        LoanCalculator loanCalculator = new LoanCalculator(loanAmount, interestRate, numYears);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(request.getLocale());
        
        try (PrintWriter out = response.getWriter()) {
            out.write(
                "<p style='color: red;'>" +
                "Loan Amount: " + currencyFormat.format(loanAmount) + "<br>" +
                "Annual Interest Rate: " + interestRate + "<br>" +
                "Number of Years: " + numYears + "<br>" +
                "Monthly payment amount: " + currencyFormat.format(loanCalculator.getMonthlyPayment()) + "<br>" +
                "Total payment amount: " + currencyFormat.format(loanCalculator.getTotalPayment()) +
                "</p>"
            );
        }
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
