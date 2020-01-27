package managedbeans;

import data_models.business_entities.Supplier;
import data_models.locations.Address;
import data_models.orders.ResupplyOrder;
import data_models.people.Employee;
import data_models.products.Car;
import data_models.products.Part;
import database_controller.DatabaseManager;
import database_controller.OrderWriter;
import exceptions.PersonNotFoundException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Kaylyn
 */
@Named(value = "resupplyOrderBean")
@SessionScoped
public class ResupplyOrderBean implements Serializable {
	
    // Constants for tax and shipping rates
    private final BigDecimal SALES_TAX_RATE = new BigDecimal(0.07);
    private final BigDecimal FLAT_SHIPPING_FEE = new BigDecimal(10.00);
    
    // Variables
    private Map<Part, Integer> listedParts = new HashMap<>();
    private Map<Part, Integer> orderedParts = new HashMap<>();
    private BigDecimal orderAmount;
    private BigDecimal orderTotal;
    private BigDecimal taxAmount;
    private Employee employee1; // only needed when not using database
    private Employee orderEmployee;
    private ResupplyOrder resupplyOrder;
    private String link;
    
    // Property variables
    private ArrayList<Part> parts = new ArrayList<>();
    private ArrayList<Part> partsSelectedByVendor = new ArrayList();
    private ArrayList<String> vendorNames = new ArrayList<>();
    private String errorMsg;
    private String orderEmployeeEmail;
    private String orderNotes;
    private String selectedVendor;

    
    
    /**
     * No-argument Constructor
     */
    public ResupplyOrderBean() {
        errorMsg = "";
        orderNotes = "";
        
// Code for the database - not working        
        try {
            DatabaseManager db = new DatabaseManager(true);
            parts = (ArrayList)db.getAllActiveParts();
        }
        catch (SQLException ex){
            errorMsg = getExceptionMsg(ex);
        }

        for (int i = 0; i < parts.size(); i++)
        { 
            if(!vendorNames.contains(parts.get(i).getSupplier().getCompanyName()))
                vendorNames.add(parts.get(i).getSupplier().getCompanyName());
        }
//// END DATABASE CODE        
// Hard-coding addresses, suppliers, parts, etc only because database not working
//        byte[] image = { 1, 2, 1 };
//        Address address1 = new Address("445 Pine Lane", "Cumming", "GA", "30041");
//        Address address2 = new Address("5 Main Street", "Greenvilee", "NC", "20055");
//        Address address3 = new Address("512 Poplar Hills Ct.", "Dunwoody", "GA", "30401");
//	employee1 = Employee.createNew("Freeman", "Freddie", "f.freeman@autopartsstore.com", "6783411165",
//                "6783411161", address3, "Due for promotion in June.", "Sales Manager");
//        Supplier supplier1 = Supplier.createNew("Auto Parts Inc.", "7705642212", address1);
//        Supplier supplier2 = Supplier.createNew("Steel Parts", "8506521111", address2);
//        Supplier supplier3 = Supplier.createNew("Auto Parts Store", "8506521112", address1);
//        Car car1 = Car.createNew("Mazda", "Miata", 2005);
//        Car car2 = Car.createNew("Toyota", "Camry", 2013);
//        Part part1 = Part.createNew(supplier1, car1, "Hub Cap", "Chrome hub cap.", "Miscellaneous", new BigDecimal(50.00), "1", 2, image);
//        Part part2 = Part.createNew(supplier1, car2, "Spark Plugs", "Standard spark plugs.", "Electrical", new BigDecimal(10.00), "50", 10, image);
//        Part part3 = Part.createNew(supplier2, car2, "Windshield", "Tinted windshield.", "Auto Body", new BigDecimal(200.00), "1", 0, image);
//        Part part4 = Part.createNew(supplier3, car2, "Windshield", "Tinted windshield.", "Auto Body", new BigDecimal(200.00), "1", 0, image);
//        parts.add(part1);
//        parts.add(part2);
//        parts.add(part3);
//        parts.add(part4);
// End hard-coding block
        
        for (int i = 0; i < parts.size(); i++)
        { 
            if(!vendorNames.contains(parts.get(i).getSupplier().getCompanyName()) && !parts.get(i).getSupplier().getCompanyName().equals("Auto Parts Store"))
                vendorNames.add(parts.get(i).getSupplier().getCompanyName());
        }
    }
    
    /**
     * loadParts method
     * used with resupplyvendor.xhtml as submit form process
     */
    public String loadParts() {
        
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).getSupplier().getCompanyName().equals(selectedVendor)) {
                partsSelectedByVendor.add(parts.get(i));
                listedParts.put(parts.get(i), 0);	
            }	
        }
        link = "resupplyorder";
        return link;
    }
    
    /**
     * backToMain method
     * used with resupplyvendor.xhtml
     */
    public String backToMain() {
        selectedVendor = null;
        link = "index";
        return link;
    }
	
    /**
     * getQuantityInCart method
     * used with resupplyorder.xhtml
     */
    public int getQuantityInCart(Part part) {
        return listedParts.get(part);
    }
    
    /**
     * addItemToCart method
     * used with resupplyorder.xhtml
     */
    public String addItemToCart(Part part) {
        int qty = listedParts.get(part);
        qty++;
	listedParts.replace(part, qty);
        link = "resupplyorder";
        return link;
    }
    
     /**
     * createResupplyOrder method
     * used with resupplyorder.xhtml
     */ 		
    public void createResupplyOrder() {
        orderAmount = new BigDecimal(0);
        for (Map.Entry<Part, Integer> items : orderedParts.entrySet()) {
            Part key = items.getKey();
            Integer value = items.getValue();
            orderAmount = orderAmount.add(key.getPricePerUnit().multiply(new BigDecimal(value)));
        }
		
	taxAmount = orderAmount.multiply(SALES_TAX_RATE);

        orderTotal = orderAmount.add(taxAmount.add(FLAT_SHIPPING_FEE));

        resupplyOrder =  ResupplyOrder.createNew(orderEmployee, FLAT_SHIPPING_FEE, orderedParts, taxAmount,
                                          orderNotes);
    }

     /**
     * processCart method
     * used with resupplyorder.xhtml as submit form process
     */
    public String processCart() {
        link = "";
        errorMsg = "";
       
        for (Map.Entry<Part, Integer> items : listedParts.entrySet()) {
            Part key = items.getKey();
            Integer value = items.getValue();
            if (value > 0) { orderedParts.put(key, value); }
        }
        
        if (orderedParts.isEmpty()) {
            errorMsg = "You do not not have any parts in your cart. Please add parts to your order before proceeding.";
            link = "resupplyorder";
            return link;
        } else if (orderEmployeeEmail == null || orderEmployeeEmail.equals("")) {
            errorMsg = "You must enter your employee email address in order to proceed.";
            link = "resupplyorder";
            return link;
//// Hard-coding verification process because database not working
//        } else if (orderEmployeeEmail.equals("f.freeman@autopartsstore.com")) {
//            orderEmployee = employee1;
//            this.createResupplyOrder();
//            link = "placeorder";
//            return link;
//        } else {
//            errorMsg = "Could not verify employee credentials.";
//            link = "resupplyorder";
//            return link;
//        }
//// END HARD CODE
//// DATABASE CODE
        } else {            
            try {
                DatabaseManager db = new DatabaseManager(true);
                orderEmployee = db.getEmployeeByEmail(orderEmployeeEmail);
                this.createResupplyOrder();
                link = "placeorder";
                return link;
            } catch (PersonNotFoundException ex) {
                errorMsg = getExceptionMsg(ex);
                orderEmployeeEmail = "";
                link = "resupplyorder";
                return link;
            } catch (SQLException ex){
                errorMsg = "Database connection error. Could not verify employee credentials.\n" + getExceptionMsg(ex);
                link = "resupplyorder";
                return link;
            }
        }
//// END DATABASE CODE
    }
    

     /**
     * resetCart method
     * used with resupplyorder.xhtml as reset form process
     * used with placeorder.xhtml as part of cancelOrder method
     */
    public void resetCart() {
	for (int i = 0; i < partsSelectedByVendor.size(); i++) {
            listedParts.put(partsSelectedByVendor.get(i), 0);	
	}
        
        orderNotes = "";
        orderEmployeeEmail = "";
        errorMsg = "";
    }
    
     /**
     * placeOrder method
     * used with placeorder.xhtml as submit form process
     */
    public String placeOrder() {
////DATABASE CODE
        try {
            DatabaseManager db = new DatabaseManager();
            OrderWriter orderWriter = new OrderWriter(db);
            orderWriter.write(resupplyOrder);
            link = "orderconfirmation";
            return link;
        } catch (SQLException ex) {
            errorMsg = "There was an error placing your order.\n + ex.getMessage()";
            link = "placeorder";
            return link;
        }
////END DATABASE CODE
////HARDCODE
//        link = "orderconfirmation";
//        return link;
////END HARDCODE
    }
    
     /**
     * cancelOrder method
     * used with placeorder.xhtml
     */
    public String cancelOrder() {
        resupplyOrder = null;
        orderedParts.clear();
        this.resetCart();
        link = "resupplyorder";
        return link;
    }
    
    private String getExceptionMsg(Exception ex) {
        // if running locally, print to console
        ex.printStackTrace();
        
        // else, update status with stack trace information and display on page
        String msg = "EXCEPTION: ";
        msg += ex.getClass().getSimpleName();
        msg += "\n";
        StackTraceElement[] elements = ex.getStackTrace();
        for (StackTraceElement element : elements) {
            msg += element.toString();
            msg += "\n";
        }
        return msg;
    }
    
    // Property methods
    public ArrayList<Part> getParts() { return parts; }   
    public ArrayList<Part> getPartsSelectedByVendor() { return partsSelectedByVendor; }
    public ArrayList<String> getVendorNames() { return vendorNames; }
    public String getErrorMsg() { return errorMsg; }
    public void setErrorMsg(String errorMsg) { this.errorMsg = errorMsg; }
    public String getOrderEmployeeEmail() { return orderEmployeeEmail; }
    public void setOrderEmployeeEmail(String orderEmployeeEmail) { this.orderEmployeeEmail = orderEmployeeEmail; }
    public String getOrderNotes() { return orderNotes; }
    public void setOrderNotes(String orderNotes) { this.orderNotes = orderNotes; }
    public String getSelectedVendor() { return selectedVendor; }
    public void setSelectedVendor(String selectedVendor) { this.selectedVendor = selectedVendor; }
    public String getVendorName(String str) { return str; }
    public int getPartID(Part part) { return part.getPartID(); }
    public String getName(Part part) { return part.getName(); }
    public String getDescription(Part part) { return part.getDescription(); }
    public String getCategory(Part part) { return part.getCategory(); }
    public String getCompanyName(Part part) { return part.getSupplier().getCompanyName(); }
    public Integer getQuantityOnHand(Part part) { return part.getQuantityOnHand(); }
    public String getPricePerUnit(Part part) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        return currencyFormat.format(part.getPricePerUnit());
    }
    public String getSubtotalCurrency() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        return currencyFormat.format(orderAmount);
    }
    public String getTaxAmountCurrency() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        return currencyFormat.format(taxAmount);
    }
    public String getShippingFeeCurrency() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        return currencyFormat.format(FLAT_SHIPPING_FEE);
    }
    public String getOrderTotalCurrency() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        return currencyFormat.format(orderTotal);
    }
}
