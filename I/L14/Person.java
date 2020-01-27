/*
    Person class definition
 */
package lesson14lab12;


/**
 * This class creates an object for a Person with name, address, & phone number
 */
public class Person {

    // instance variables
    private String name;
    private String address;
    private String phone;

    /**
     * Default no-arg constructor
     */
    public Person() {
        name = "";
        address = "";
        phone = "";
    }

    /**
     * Constructor with arguments
     * @param n     name
     * @param a     address
     * @param p     phone
     */
    public Person(String n, String a, String p) {
        name = n;
        address = a;
        phone = p;
    }

    /**
     * Mutator for name
     * @param n     name
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Mutator for address
     * @param a
     */
    public void setAddress(String a) {
        address = a;
    }

    /**
     * Mutator for phone number
     * @param p
     */
    public void setPhone(String p) {
        phone = p;
    }

    /**
     * Getter for name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter for phone number
     * @return
     */
    public String getPhone() {
        return phone;
    }
}
