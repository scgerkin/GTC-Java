/*
    Name: Stephen Gerkin
    Date: 02/08/2019
    Lesson 06 Lab 5 Program 1
    Program Title:
        Hotel Occupancy
    Program Description:
        Program to get a number of floors in a hotel, rooms per floor,
        and occupied rooms per floor. Calculates and displays occupancy rate.

 */

package lesson.pkg6.lab.pkg5.program.pkg1;

import javax.swing.JOptionPane;

/**
 * Class definition
 */
public class Lesson6Lab5Program1 {

    /**
     * Main method body
     */
    public static void main() {
        int     topFloor,
                startFloor,
                roomsOnFloor,
                occupiedRoomsOnFloor,
                totalRoomsInHotel = 0,
                totalOccupiedRoomsInHotel = 0;
        
        topFloor = getTopFloor();
        startFloor = 1;
        
        for (int i = startFloor; i <= topFloor; i++) {
            roomsOnFloor = getRoomsOnFloor(i);
            totalRoomsInHotel += roomsOnFloor;
            
            occupiedRoomsOnFloor = getOccupiedRoomsOnFloor(i, roomsOnFloor);
            totalOccupiedRoomsInHotel += occupiedRoomsOnFloor;
        }
        
        displayTotals(totalRoomsInHotel, totalOccupiedRoomsInHotel);
    }
    /**
    * Name:
    *	getTopFloor
    * Parameters:
    *   none
    * Return:
    *	@return     number of floors in hotel
    * Description:
    *	Gets the top floor number for hotel
    */
    public static int getTopFloor() {
        int returnValue;
        
        do {
            returnValue = Integer.parseInt(JOptionPane.showInputDialog("Please enter the top floor number of the hotel"));
            
            if (returnValue <= 0) {
                JOptionPane.showMessageDialog(null,"That was not a valid number.\nPlease enter a positive integer value.");
            }
        } while (returnValue <= 0);
        
        return returnValue;
    }
    
    /**
    * Name:
    *	getRoomsOnFloor
    * Parameters:
    *   @param	floor   floor number to get info for
    * Return:
    *	@return     number of rooms on floor
    * Description:
    *	gets number of rooms on a floor, does not allow value less than 10
    */
    public static int getRoomsOnFloor(int floor) {
        int returnValue;
        
        do {
            returnValue = Integer.parseInt(JOptionPane.showInputDialog("Please enter the total number of rooms on floor " + floor + "."));
            
            if (returnValue < 10) {
                JOptionPane.showMessageDialog(null,"That was not a valid entry.\nEach floor must have at least 10 rooms.");
            }
        } while (returnValue < 10);
        
        return returnValue;
    }
    /**
    * Name:
    *	getOccupiedRoomsOnFloor
    * Parameters:
    *   @param	floor   floor number to get info for
    *   @param  maxRooms    max # of rooms that can be occupied
    * Return:
    *	@return     number of occupied rooms on floor
    * Description:
    *	gets number of occupied rooms on a floor, does not allow value greater than number of rooms for the floor
    */
    public static int getOccupiedRoomsOnFloor(int floor, int maxRooms) {
        int returnValue;
        
        do {
            returnValue = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of occupied rooms on floor " + floor + "."));
            
            if (returnValue > maxRooms) {
                JOptionPane.showMessageDialog(null,"That was not a valid entry.\nThere cannot be more occupied rooms than total rooms on the floor.");
            }
        } while (returnValue > maxRooms);
        
        return returnValue;
    }
    
    /**
    * Name:
    *	displayTotals
    * Parameters:
    *   @param	totalRooms  total number of rooms in the hotel
    *   @param  occupiedRooms    total number of occupied rooms in the hotel
    * Return:
    *	none
    * Description:
    *	Displays total rooms in hotel, total occupied rooms, # of vacancies, and occupancy rate
    */
    public static void displayTotals(int totalRooms, int occupiedRooms) {
        
        String line1 = "The total number of rooms in the hotel is " + totalRooms + ".\n";
        String line2 = "The number of occupied rooms in the hotel is " + occupiedRooms + ".\n";
        String line3 = "The number of vacant rooms is " + (totalRooms - occupiedRooms) + ".\n";
        String line4 = String.format("The occupancy rate is %.2f", (((1.0*occupiedRooms) / totalRooms)*100.0)) + "%.";
        
        JOptionPane.showMessageDialog(null, line1 + line2 + line3 + line4);
    }
}
