package administration;

import data_models.business_entities.Supplier;
import data_models.locations.Address;
import data_models.people.Employee;
import data_models.products.Car;
import data_models.products.Part;
import database_controller.DatabaseManager;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.List;

/**
 *
 * @author Shuning
 */
@SessionScoped
@ManagedBean(name = "administrations")
public class Administration implements Serializable {

    protected List<Employee> employee = new ArrayList<>();
    protected List<Supplier> supplier = new ArrayList<>();
    protected List<Part> parts = new ArrayList<>();
    protected List<Part> partsOfSupplier = new ArrayList<>();
    protected int id;
    protected int employeeID;
    private String lastName;
    private String firstName;
    private String email;
    private String primaryPhone;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String secondaryPhone;
    private String notes;
    private String title;

    private String action; // action for button
    private String status; // text to show
    private int businessID;
    private String companyName;
    private String contactName;
    private String website;

    private Integer partID;
    private Integer supplierID;
    private Integer carID;
    private String name;
    private String description;
    private String category;
    private BigDecimal pricePerUnit;
    private String quantityPerUnit;
    private boolean discontinued;
    private String make;
    private String model;
    private int productionYear;

    DatabaseManager dbManager;

    public List<Part> getPartsOfSupplier() {
        partsOfSupplier = new ArrayList<>();
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).getSupplier().getBusinessID().equals(supplierID)) {
                partsOfSupplier.add(parts.get(i));
            }
        }
        return partsOfSupplier;
    }

    /**
     * Creates a new instance of Administration
     */
    public Administration() {
        carID = 0;
        try {
            dbManager = new DatabaseManager();
        }
        catch (Exception ex) {
            printException(ex);
        }
        loadEmployees();
    }

    private void loadEmployees() {
        try {
            dbManager.connect();
            employee = dbManager.getAllEmployees();
            status ="<p style=\"color:green\">DB List loaded succesfull!</p> ";
        }
        catch (Exception ex) {
            printException(ex);
        }
    }
    
    /*
        Does all the actions when a button is clicked
     */
    public String processSubmit() {
        String link = "";
        status = "";
        Address a = null;
        Car c = null;
        // there is one variable action which defines which action should the button do. Changing an employee or adding or deleting
        switch (action) {
            case "deleteEmployee":
                link = "employeeDeleted";
                employee.remove(id);
                break;
            case "deleteVendor":
                link = "vendorDeleted";
                supplier.remove(id);
                break;
            case "deleteParts":
                link = "partsDeleted";
                parts.remove(id);
                break;
            case "changeEmployee":
                link = "employeeChanged";
                status = "";

                // This one has try and catch because the telephone number, state and zip has to follow certain standard
                try {
                    employee.get(id).setEmail(email);
                    employee.get(id).setFirstName(firstName);
                    employee.get(id).setLastName(lastName);
                    try {
                        employee.get(id).setPrimaryPhone(primaryPhone);
                        employee.get(id).setSecondaryPhone(secondaryPhone);
                    }
                    catch (Exception ex) {
                        printException(ex);
                        link = "";
                    }
                    
                    employee.get(id).setTitle(title);
                    employee.get(id).setNewNotes(notes);
                    
                    try {
                        a = new Address(street, city, state, zipCode);
                    }
                    catch (Exception ex) {
                        printException(ex);
                        link = "";
                    }
                    
                    employee.get(id).setAddress(a);
                    
                }
                catch (Exception ex) {
                    printException(ex);
                    link = "";
                }

                break;
            case "changeVendor":
                link = "vendorChanged";
                status = "";

                // This one has try and catch because the telephone number, state and zip has to follow certain standard
                try {
                    supplier.get(id).setWebsite(website);
                    supplier.get(id).setCompanyName(companyName);
                    supplier.get(id).setContactName(contactName);
                    try {
                        supplier.get(id).setPrimaryPhone(primaryPhone);
                        supplier.get(id).setSecondaryPhone(secondaryPhone);
                    }
                    catch (Exception ex) {
                        printException(ex);
                        link = "";
                    }
                    supplier.get(id).setNewNotes(notes);
                    try {
                        a = new Address(street, city, state, zipCode);
                    }
                    catch (Exception ex) {
                        printException(ex);
                        link = "";
                    }
                    supplier.get(id).setAddress(a);
                } catch (Exception ex) {
                    printException(ex);
                    link = "";
                }

                break;

            case "changeParts":
                link = "partsChanged";
                
                status = "";
                try {
                    c = dbManager.retrieveCarConfigByID(parts.get(id).getCar().getCarID());
                    parts.get(id).setCar(c);
                }
                catch (Exception ex) {
                    printException(ex);
                }
                
                try {
                    parts.get(id).setCategory(category);
                    parts.get(id).setName(name);
                    parts.get(id).setDescription(description);
                    parts.get(id).setPricePerUnit(pricePerUnit);
                    parts.get(id).setQuantityPerUnit(quantityPerUnit);
                    parts.get(id).setDiscontinued(discontinued);
                }
                catch (Exception ex) {
                    printException(ex);
                    link = "";
                }

                break;

            case "addEmployee":
                link = "employeeAdded";
                status = "";
                try {
                    a = new Address(street, city, state, zipCode);
                }
                catch (Exception ex) {
                    printException(ex);
                    link = "";
                }
                
                try {
                    Employee newEmployee = Employee.createNew(lastName, firstName, email, primaryPhone, secondaryPhone, a, notes, title);
                    dbManager.saveToDatabase(newEmployee);
                    employee.add(newEmployee);
                }
                catch (Exception ex) {
                    printException(ex);
                    link = "";
                }

                break;
            case "addVendor":
                link = "vendorAdded";
                status = "";
                try {
                    a = new Address(street, city, state, zipCode);
                }
                catch (Exception ex) {
                    status = "<p style=\"color:red\">" + ex.getMessage() + "</p>";
                    link = "";
                }
                try {
                    Supplier newSupplier = Supplier.createNew(companyName, contactName, primaryPhone, secondaryPhone, website, a, notes);
                    dbManager.saveToDatabase(newSupplier);
                    supplier.add(newSupplier);
                }
                catch (Exception ex) {
                    printException(ex);
                    link = "";
                }

                break;
            case "addParts":
                link = "partsAdded";
                status = "";
                carID++;
                try {
                    c = dbManager.retrieveCarConfigByID(carID);
                }
                catch (Exception ex) {
                    printException(ex);
                    link = "";
                }
                try {
                    Part newPart = Part.createNew(this.getSupplierByID(supplierID), c, name, description, category, pricePerUnit, quantityPerUnit, 100, null);
                    dbManager.saveToDatabase(newPart);
                    parts.add(newPart);

                }
                catch (Exception ex) {
                    printException(ex);
                    link = "";
                }

                break;
        }

        /* Change the status if adding employee with invalid state or zip code */
        if (action.equals("addEmployee") || action.equals("changeEmployee") || action.equals("addVendor") || action.equals("deleteVendor")) {

            // Error handling
            if (state.length() != 2) {
                status = "<p style=\"color:red\">States can only be 2 characters</p>";
            }

            if (zipCode.length() != 10 && zipCode.length() != 5) {
                status = "<p style=\"color:red\">Zip code must be only 5 or 10 characters.</p>";
            }

        }
        return link;
    }

    public Supplier getSupplierByID(int id) {
        Supplier s = null;
        for (int i = 0; i < supplier.size(); i++) {
            if (supplier.get(i).getBusinessID() == id) {
                s = supplier.get(i);
            }
        }
        return s;
    }

    public void resetForm() {
        email = "";
        firstName = "";
        lastName = "";
        primaryPhone = "";
        secondaryPhone = "";
        title = "";
        notes = "";
        street = "";
        city = "";
        state = "";
        zipCode = "";
        employeeID = 0;
    }
    
    private void printException(Exception ex) {
        // if running locally, print to console
        ex.printStackTrace();
        
        // else, update status with stack trace information and display on page
        status = "<p style=\"color:red;\">EXCEPTION: ";
        status += ex.getClass().getSimpleName();
        status += "<br/>";
        StackTraceElement[] elements = ex.getStackTrace();
        for (StackTraceElement element : elements) {
            status += element.toString();
            status += "<br/>";
        }
    }

    // transform char[] to String
    public String getEmployeeStateAsString(int employeeId) {
        String s = String.valueOf(employee.get(employeeId).getAddress().getState());
        state = s;
        return s;
    }

    // transform char[] to String
    public String getEmployeeZipAsString(int employeeId) {
        String s = String.valueOf(employee.get(employeeId).getAddress().getZipCode());
        zipCode = s;
        return s;
    }

    public String getSupplierStateAsString(int businessID) {
        String s = String.valueOf(supplier.get(businessID).getAddress().getState());
        state = s;
        return s;
    }

    // transform char[] to String
    public String getSupplierZipAsString(int businessID) {
        String s = String.valueOf(supplier.get(businessID).getAddress().getZipCode());
        zipCode = s;
        return s;
    }
    
    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
    
    public void setPartsOfSupplier(ArrayList<Part> partsOfSupplier) {
        this.partsOfSupplier = partsOfSupplier;
    }

    public Integer getPartID() {
        return partID;
    }

    public void setPartID(Integer partID) {
        this.partID = partID;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public Integer getCarID() {
        return carID;
    }

    public void setCarID(Integer carID) {
        this.carID = carID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public DatabaseManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public int getBusinessID() {
        return businessID;
    }

    public void setBusinessID(int businessID) {
        this.businessID = businessID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Supplier> getSupplier() {
        return supplier;
    }

    public void setSupplier(ArrayList<Supplier> supplier) {
        this.supplier = supplier;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(ArrayList<Part> parts) {
        this.parts = parts;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}