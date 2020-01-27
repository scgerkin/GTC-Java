package addressbook;

import java.util.Objects;

/**
 * POJO object for storing an Address
 */
public class Address {
    private String name;
    private String street;
    private String city;
    private String state;
    private String zipcode;

    /**
     * Constructor requires all fields, however, currently an empty string can be
     * in any one field
     * @param name Full name address field
     * @param street Street address field
     * @param city City address field
     * @param state State address field
     * @param zipcode Zipcode address field
     */
    public Address(String name, String street, String city, String state, String zipcode) {
        // TODO Test empty and null strings
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return name + "\n" +
               street + "\n" +
               city + ", " + state + " " + zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getName(), address.getName()) &&
               Objects.equals(getStreet(), address.getStreet()) &&
               Objects.equals(getCity(), address.getCity()) &&
               Objects.equals(getState(), address.getState()) &&
               Objects.equals(getZipcode(), address.getZipcode());
    }
}
