package parkingticketsimulator;

import java.text.DecimalFormat;

/**
 *
 * @author Stephen Gerkin
 * @date 2019-05-23
 * Programming Lab 3 - 8. Parking Ticket Simulator
 * Program to simulate a police officer issuing a parking ticket
 */
public class Main {

    public static final DecimalFormat df = new DecimalFormat("$###,##0.00");
    /**
     * Main method demonstrates ParkingTicketSimulator classes with some test
     * cases
     * @param args command line args
     */
    public static void main(String[] args) {
        ParkedCar car =
            new ParkedCar("Abandoned", "Vehicle", "Blue", "ABC123", 10_000);
        ParkingMeter meter = new ParkingMeter(0);
        PoliceOfficer officer = new PoliceOfficer("Fred Jones", "12345");

        // TEST 1
        // fine for this should be $1,685.00
        System.out.println("Test 1: Fine = $1,685.00");

        ParkingTicket ticket = officer.issueTicket(car, meter);

        if (ticket.getFine() == 1_685) {
            System.out.println("PASS");
            System.out.println(df.format(ticket.getFine()));
        }
        else {
            System.out.println("FAIL");
            System.out.println(df.format(ticket.getFine()));
        }
        System.out.println();

        // TEST 2
        // fine for this should be $855.00
        System.out.println("Test 2: Fine = $855.00");

        meter = new ParkingMeter(5_000);
        ticket = officer.issueTicket(car, meter);

        if (ticket.getFine() == 855) {
            System.out.println("PASS");
            System.out.println(df.format(ticket.getFine()));
        }
        else {
            System.out.println("FAIL");
            System.out.println(df.format(ticket.getFine()));
        }
        System.out.println();

        // TEST 3
        // fine for this should be $25.00
        System.out.println("Test 3: Fine = $25.00");

        car = new ParkedCar("Just", "Parked", "Black", "ZXY987", 1);
        meter = new ParkingMeter(0);
        ticket = officer.issueTicket(car, meter);

        if (ticket.getFine() == 25) {
            System.out.println("PASS");
            System.out.println(df.format(ticket.getFine()));
        }
        else {
            System.out.println("FAIL");
            System.out.println(df.format(ticket.getFine()));
        }
        System.out.println();

        // TEST 4
        // fine for this should be $0.00
        System.out.println("Test 4: Fine = $0.00");

        car = new ParkedCar("Just", "Parked", "Black", "ZXY987", 0);
        ticket = officer.issueTicket(car, meter);

        if (ticket.getFine() == 0) {
            System.out.println("PASS");
            System.out.println(df.format(ticket.getFine()));
        }
        else {
            System.out.println("FAIL");
            System.out.println(df.format(ticket.getFine()));
        }
        System.out.println();

        // TEST 5
        // officer.determineTicket() should be false
        System.out.println("Test 5: officer.determineTicket() should be false");

        if (!officer.determineTicket(car, meter)) {
            System.out.println("PASS");
            System.out.println(officer.determineTicket(car, meter));
        }
        else {
            System.out.println("FAIL");
            System.out.println(officer.determineTicket(car, meter));
        }
        System.out.println();

        // TEST 6
        // officer.determineTicket() should be true
        System.out.println("Test 6: officer.determineTicket() should be true");

        car = new ParkedCar("Just", "Parked", "Black", "ZXY987", 1);
        meter = new ParkingMeter(0);

        if (officer.determineTicket(car, meter)) {
            System.out.println("PASS");
            System.out.println(officer.determineTicket(car, meter));
        }
        else {
            System.out.println("FAIL");
            System.out.println(officer.determineTicket(car, meter));
        }
        System.out.println();


        System.exit(0);
    }
}
