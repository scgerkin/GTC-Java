package employeeclass;

/**
 *  @author Stephen Gerkin
 *  @date 2019-05-15
 *  Programming Lab 2 - 1. Employee Class
 *  Employee class to store fields about an employee
 */
public class Main {

    /**
     *  Main method to demonstrate Employee class
     *  @param args command line args
     */
    public static void main(String[] args) {
        // full arg constructor demo
        Employee susan = new Employee("Susan Meyers", 47899,
                                      "Accounting", "Vice President");

        // 2 arg constructor demo
        Employee mark = new Employee("Mark Jones", 39119);
        mark.setDepartment("IT");
        mark.setPosition("Programmer");

        // no arg constructor demo & all mutator methods
        Employee joy = new Employee();
        joy.setName("Joy Rogers");
        joy.setIdNumber(81774);
        joy.setDepartment("Manufacturing");
        joy.setPosition("Engineer");

        // all fields accessor demo
        System.out.println(susan.getEmployeeFields());
        System.out.println();

        // all accessor method demo
        System.out.println("Name: " + joy.getName());
        System.out.println("ID Number: " + joy.getIdNumber());
        System.out.println("Department: " + joy.getDepartment());
        System.out.println("Position: " + joy.getPosition());
        System.out.println();

        // toString override demo
        System.out.println(mark);

        System.exit(0);
    }
}
