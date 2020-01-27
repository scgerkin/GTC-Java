package addressbook;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Stephen Gerkin<br>
 * Programming Lab 01 - AddressBook<br>
 * Program to practice in using a RandomAccessFile object for storing strings
 * of fixed length.<br>
 * <br>
 * This driver currently only contains basic test suites for demonstrating the
 * AddressBookFile methods are working as intended.
 */
public class Driver {
    private static ArrayList<Address> debug_AddressList_5;
    private static String debug_FreshFilePathName = "debug_files/fresh.dat";
    private static File debug_FreshFileObj;
    private static String debug_ExistingFilePathName = "debug_files/existing.dat";
    private static File debug_ExistingFileObj;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initDebugMembers();

        debug_CreateNewAddressBookFile();
        debug_AttemptingToUseReadOnlyFileThrowsIllegalArgumentException();
        debug_AttemptingToTraverseEmptyFileThrowsNullPointers();
        debug_UsingGetAllRecordsOnEmptyFileReturnsEmptyList();
        debug_AssertHasRecordsMethodWorksAsIntended();
    }

    private static void debug_AssertHasRecordsMethodWorksAsIntended() {
        debug_DeleteFreshFile();
        AddressBookFile abf = new AddressBookFile(debug_FreshFileObj);

        assert !abf.hasRecords() : "Empty file asserts it has records.";

        // demonstration purposes for asserts not being enabled on debug
        if (!abf.hasRecords()) {
            System.out.println("hasRecords method performing correctly for empty file");
        }
        else {
            System.err.println("hasRecords method did not perform correctly for empty file");
        }

        debug_CreateExistingFile();

        abf = new AddressBookFile(debug_ExistingFileObj);

        assert abf.hasRecords() : "Existing file asserts it has no records";
        if (abf.hasRecords()) {
            System.out.println("hasRecords method performing correctly for existing file");
        }
        else {
            System.err.println("hasRecords method did not perform correctly for existing file");
        }
    }

    private static void debug_UsingGetAllRecordsOnEmptyFileReturnsEmptyList() {
        debug_DeleteFreshFile();
        AddressBookFile abf = new AddressBookFile(debug_FreshFileObj);
        ArrayList<Address> list = abf.getAllRecords();

        if (list.isEmpty()) {
            System.out.println("getAllRecords successfully returned empty list on empty file");
        }
        else {
            System.err.println("getAllRecords did NOT return an empty list on empty file");
        }
    }

    private static void debug_AttemptingToTraverseEmptyFileThrowsNullPointers() {
        debug_DeleteFreshFile();
        AddressBookFile abf = new AddressBookFile(debug_FreshFileObj);

        try {
            System.out.println("Attempting to use getFirst on empty file");
            System.out.println(abf.getFirst());
            System.err.println("EXPECTED EXCEPTION NOT THROWN");
        }
        catch (NullPointerException e) {
            System.out.println("Null Pointer Exception caught successfully");
        }

        try {
            System.out.println("Attempting to use getNext on empty file");
            System.out.println(abf.getNext());
            System.err.println("EXPECTED EXCEPTION NOT THROWN");
        }
        catch (NullPointerException e) {
            System.out.println("Null Pointer Exception caught successfully");
        }

        try {
            System.out.println("Attempting to use getCurrent on empty file");
            System.out.println(abf.getCurrent());
            System.err.println("EXPECTED EXCEPTION NOT THROWN");
        }
        catch (NullPointerException e) {
            System.out.println("Null Pointer Exception caught successfully");
        }

        try {
            System.out.println("Attempting to use getPrevious on empty file");
            System.out.println(abf.getPrevious());
            System.err.println("EXPECTED EXCEPTION NOT THROWN");
        }
        catch (NullPointerException e) {
            System.out.println("Null Pointer Exception caught successfully");
        }

        try {
            System.out.println("Attempting to use getLast on empty file");
            System.out.println(abf.getLast());
            System.err.println("EXPECTED EXCEPTION NOT THROWN");
        }
        catch (NullPointerException e) {
            System.out.println("Null Pointer Exception caught successfully");
        }

    }

    private static void debug_AttemptingToUseReadOnlyFileThrowsIllegalArgumentException() {
        debug_CreateExistingFile();
        debug_ExistingFileObj.setWritable(false);

        try {
            System.out.println("Attempting to use read only file, should catch illegal argument exception");
            AddressBookFile abf = new AddressBookFile(debug_ExistingFilePathName);
            System.err.println("EXPECTED EXCEPTION NOT THROWN");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Illegal argument exception caught successfully");
        }
    }

    private static void debug_CreateNewAddressBookFile() {
        debug_DeleteFreshFile();
        AddressBookFile abf = new AddressBookFile(debug_FreshFilePathName);

        for (Address address : debug_AddressList_5) {
            abf.addNew(address);
        }

        System.out.println(abf.getCurrent());   // trimmed
        System.out.println();
        System.out.println(abf.getPrevious());  // scooby doo
        System.out.println();
        System.out.println(abf.getFirst());     // me
        System.out.println();
        System.out.println(abf.getNext());      // john smith
        System.out.println();
        System.out.println(abf.getCurrent());   // john smith
        System.out.println();
        System.out.println(abf.getNext());      // mary jane
        System.out.println();
        System.out.println(abf.getLast());      // trimmed
        System.out.println();

        // test updating current
        abf.getFirst();
        abf.getNext();
        abf.updateCurrent(new Address("New Address", "New Address", "New Address", "NA", "33333"));

        System.out.println(abf.getCurrent());   // new address
        System.out.println();
        System.out.println(abf.getLast());      // trimmed
        System.out.println();
        System.out.println(abf.getFirst());     // me
        System.out.println();
        System.out.println(abf.getNext());      // new address
        System.out.println();

        System.out.println(abf.getNumRecords());    // 5
        System.out.println();

        ArrayList<Address> addressList2 = abf.getAllRecords();

        System.out.println("\nPrinting getAllRecords:\n" +
                               "Order should be\n" +
                               "Me -> New Address -> Mary Jane -> Scooby Doo -> Trimmed");
        // me, new, mary jane, scooby doo, trimmed
        for (Address address : addressList2) {
            System.out.println(address);
            System.out.println();
        }

    }

    private static void initDebugMembers() {
        debug_AddressList_5 = new ArrayList<Address>() {
            {
                add(new Address("Stephen Gerkin",
                    "3291 Francine Dr.", "Decatur", "GA", "30033"));
                add(new Address("John Smith",
                    "1234 Main Street", "Albuquerque", "NM", "99999"));
                add(new Address("Mary Jane",
                    "420 Reefer Way", "Boulder", "CO", "42069"));
                add(new Address("Scooby Doo",
                    "1986 Mystery Lane", "Hanna Barbara", "MN", "24987"));
                add(new Address("Incredibly long name that should get trimmed",
                    "Ridiculously long street that should also get trimmed",
                    "Unbelievably long city name that should get trimmed",
                    "Long state, trim it up",
                    "123456789"));
            }
        };
        debug_CreateExistingFile();
        debug_DeleteFreshFile();
    }

    private static void debug_CreateExistingFile() {
        debug_ExistingFileObj = new File(debug_ExistingFilePathName);
        if (debug_ExistingFileObj.exists()) {
            debug_ExistingFileObj.delete();
        }

        AddressBookFile abf = new AddressBookFile(debug_ExistingFilePathName);
        for (Address address : debug_AddressList_5) {
            abf.addNew(address);
        }
    }

    private static void debug_DeleteFreshFile() {
        debug_FreshFileObj = new File(debug_FreshFilePathName);
        if (debug_FreshFileObj.exists()) {
            debug_FreshFileObj.delete();
        }
    }
}
