package employeeclass;

/**
 * Employee class stores information about an employee
 * Provides constructors for varying amounts of information
 * Provides mutators and accessors for all fields
 */
public class Employee {

    // class fields
    private String name;
    private int idNumber;
    private String department;
    private String position;

    /**
     * Class no-arg constructor
     */
    public Employee() {
        this.name = "";
        this.idNumber = 0;
        this.department = "";
        this.position = "";
    }

    /**
     * Class constructor for provided arguments
     * @param empName
     * @param empId
     */
    public Employee(String empName, int empId) {
        this.name = empName;
        this.idNumber = empId;
        this.department = "";
        this.position = "";
    }

    /**
     * Class construtor for provided arguments
     * @param empName
     * @param empId
     * @param empDepartment
     * @param empPosition
     */
    public Employee(String empName, int empId,
                    String empDepartment, String empPosition) {
        this.name = empName;
        this.idNumber = empId;
        this.department = empDepartment;
        this.position = empPosition;
    }

    /**
     * Name field mutator
     * @param empName
     */
    public void setName(String empName) {
        this.name = empName;
    }

    /**
     * ID Number field mutator
     * @param empId
     */
    public void setIdNumber(int empId) {
        this.idNumber = empId;
    }

    /**
     * Department field mutator
     * @param empDepartment
     */
    public void setDepartment(String empDepartment) {
        this.department = empDepartment;
    }

    /**
     * Position field mutator
     * @param empPosition
     */
    public void setPosition(String empPosition) {
        this.position = empPosition;
    }

    /**
     * All fields mutator
     * @param empName
     * @param empId
     * @param empDepartment
     * @param empPosition
     */
    public void setEmployeeFields(String empName, int empId,
                                  String empDepartment, String empPosition) {
        this.name = empName;
        this.idNumber = empId;
        this.department = empDepartment;
        this.position = empPosition;
    }

    /**
     * Accessor for name
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accessor for ID Number
     * @return int
     */
    public int getIdNumber() {
        return this.idNumber;
    }

    /**
     * Accessor for department
     * @return String
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     * Accessor for position
     * @return String
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * Accessor for all class fields
     * @return String
     */
    public String getEmployeeFields() {
        return this.toString();
    }

    /**
     * toString method override
     * @return Class object as string
     */
    @Override
    public String toString() {
        return (
            "Name: " + this.name + "\n"
            + "ID Number: " + this.idNumber + "\n"
            + "Department: " + this.department + "\n"
            + "Position: " + this.position + "\n"
        );
    }
}
