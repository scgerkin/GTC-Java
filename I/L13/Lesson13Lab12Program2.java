/*
Name: Stephen Gerkin
Date: 04/02/2019
Lesson 13 Lab 12 Program 2
Program Title:
    Parking Ticket Simulator
Program Description:
    This program creates 4 classes and demonstrates each
 */
package lesson13lab12program2;

/**
 *  This program demonstrates the ParkedCar, ParkingMeter, ParkingTicket, and
 *  PoliceOfficer classes
 * @author sgerkin
 */
public class Lesson13Lab12Program2 {

    /**
     * Main method definition
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // first demonstration using new car, meter, and officer
        ParkedCar car1 = new ParkedCar("CHEVY", "SONIC", "GREY", "3QZ", 70);
        ParkingMeter meter = new ParkingMeter(10);
        PoliceOfficer officer = new PoliceOfficer("DUNN", "DIRK", 1234);
        
        // demonstrate determination
        if (officer.determineTicket(car1, meter)) {
            ParkingTicket ticket =
                    new ParkingTicket(officer.issueTicket(car1, meter));
            
            ticket.reportCar();
            ticket.reportFine();
            ticket.reportOfficer();
        }
        
        System.out.println("\n");
        
        // second demonstration using a new car, same meter, and same officer
        // without decision
        ParkedCar testCar2 =
                new ParkedCar("FORD", "MUSTANG", "RED", "75432", 300);
        
        ParkingTicket ticket =
                new ParkingTicket(officer.issueTicket(testCar2, meter));
        
        ticket.reportCar();
        ticket.reportFine();
        ticket.reportOfficer();
        
        System.out.println("\n");
        
        // third demonstration using new car, new meter, new officer, no fine
        ParkedCar testCar3 = new ParkedCar("VW", "BEETLE", "BLUE", "H3X89", 20);
        ParkingMeter meter2 = new ParkingMeter(20);
        PoliceOfficer officer2 = new PoliceOfficer("JONES", "ALBERT", 58320);
        
        if (officer2.determineTicket(testCar3, meter2)) {
            // object will not be created or used
            ParkingTicket ticket2 =
                    new ParkingTicket(officer2.issueTicket(testCar3, meter2));
        
            ticket2.reportCar();
            ticket2.reportFine();
            ticket2.reportOfficer();
        }
        
        // change meter value
        meter2.setMinsPurchased(10);
        
        // now a ticket will be issued
        if (officer2.determineTicket(testCar3, meter2)) {
            ParkingTicket ticket2 =
                    new ParkingTicket(officer2.issueTicket(testCar3, meter2));
        
            ticket2.reportCar();
            ticket2.reportFine();
            ticket2.reportOfficer();
        }
        
        System.out.println("\n");
        
        // final demonstration, new car with no fine, but create object without
        // using decision structure to show object can still be used
        ParkedCar testCar4 = new ParkedCar("KIA", "SORENTO", "ORANGE", "ZB2U2",
                                           0);
        
        ParkingTicket ticket3 =
                new ParkingTicket(officer2.issueTicket(testCar4, meter2));
        
        ticket3.reportCar();
        ticket3.reportFine();
        ticket3.reportOfficer();

        // terminate JVM with success
        System.exit(0);
    }
}
