/*
*   Name: Stephen Gerkin
*   Date: 02/26/2019
*   Midterm Project Part 2
*   Program Title:
*       Cell Phone Service
*   Program Description:
*       Program to get cell service account info from user, determines plan type and charges
*       Displays info to user
*
*/
 
package midterm.part2;

import javax.swing.JOptionPane;
import java.text.NumberFormat;


/**
 * Class definition
 */
public class MidtermPart2 {

    /**
     * Main method
     */
    public static void main(String[] args) {
        int     acctNo;
        char    service;
		String 	serviceName;
        double  bill;
        
        acctNo = getAcctNo();
        
        service = getServiceCode();
        
        if (service == 'R') {
            bill = regularBill();
            serviceName = "regular";
        }
        else {
            bill = premiumBill();
            serviceName = "premium";
        }
        
        displayBill(acctNo, serviceName, bill);
		
        System.exit(0);
    }
	
    /**
    * Name:
    *	getAcctNo
    * Parameters:
    *   none
    * Return:
    *	@return     account number as int
    * Description:
    *	Gets account number from user
    */
    public static int getAcctNo() {
        return Integer.parseInt(JOptionPane.showInputDialog("Enter your account number."));
    }
	
    /**
	* Name:
	*	getServiceCode
	* Parameters:
	*	none
	* Return:
	*	@return		service code as char
	* Description:
	*	Gets service type from user
	*/
    public static char getServiceCode() {
        
        char serviceCode;
                
        do {
            serviceCode = Character.toUpperCase(JOptionPane.showInputDialog("Enter the service code for your service (R/P)").charAt(0));

            if (serviceCode != 'R' && serviceCode != 'P') {
                JOptionPane.showMessageDialog(null, "That was not a valid entry.");
            }

        } while (serviceCode != 'R' && serviceCode != 'P');
        
        return serviceCode;
    }
	
    /**
	* Name:
	*	regularBill
	* Parameters:
	*	none
	* Return:
	*	@return		charges for regular service as double
	* Description:
	*	Gets minutes used for regular service and calcs/returns charges
	*/
    public static double regularBill() {
        int minutesUsed = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of minutes used."));
        double charges = 10.00;

        if (minutesUsed > 50){
            charges += (minutesUsed-50)*(0.20);
        }
        
        return charges;
    }
	
	/**
	* Name:
	*	premiumBill
	* Parameters:
	*	none
	* Return:
	*	@return		charges for premium service as double
	* Description:
	*	Gets minutes used for premium service and calcs/returns charges
	*/
    public static double premiumBill() {
        int dayMinutes = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of minutes used between 6:00am to 6:00pm.\n(Daytime Minutes)"));
        int nightMinutes = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of minutes used between 6:00pm to 6:00am.\n(Nighttime Minutes)"));
        double charges = 25.00;
        
        if (dayMinutes > 75) {
            charges += (dayMinutes-75)*0.10;
        }

        if (nightMinutes > 100) {
            charges += (nightMinutes-100)*0.05;
        }
        
        return charges;
    }
	
    /**
	* Name:
	*	displayBill
	* Parameters:
	*	@param	acctNo	account number for user
	*	@param	bill	total bill for user
	* Return:
	*	none
	* Description:
	*	Displays account number and bill to user
	*/
    public static void displayBill(int acctNo, String serviceName, double bill) {
        String output = "";
        NumberFormat money = NumberFormat.getCurrencyInstance();
        
        output += "Account number: " + acctNo + "\n";
		output += "This account has " + serviceName + " service.\n";
        output += "Amount due: " + money.format(bill);
        
        JOptionPane.showMessageDialog(null, output);
    }
}
