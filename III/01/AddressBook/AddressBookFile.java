package addressbook;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StreamCorruptedException;

import java.util.ArrayList;

/**
 * AddressBookFile class provides methods for interacting with a RandomAccessFile
 * that contains Address objects of specific size indicated by program requirements.<br>
 *<br>
 * This class works similarly to a linked list. It maintains a pointer to the head of the file
 * and a pointer that traverses the file record by record.<br>
 *<br>
 * This is an exercise in using a RandomAccessFile with fixed length strings rather than using
 * delimiters for each field. As such, the decision was made to store the size of each field
 * for the file inside this class as the Address class can be used as a simple POJO for Addresses
 * with fields of varying size.<br>
 *<br>
 * Because of the nature of needing to store a string as a fixed length, all strings are converted
 * to character arrays with null character padding up to the length of individual field before being
 * written to the file. If a string does not fit the length specified, it is either trimmed or padded.<br>
 *<br>
 * For example, if this file receives an Address object with the field state as "GEORGIA", before being
 * written to file, state will be trimmed to "GE".<br>
 *<br>
 * For an example of a string that is shorter than the array, consider "Smith"
 * for a field of name and a MAX_NAME_LENGTH = 10.<br>
 * The name will be converted to a char array as such:<br>
 * {'S', 'm', 'i', 't', 'h', \0, \0, \0, \0, \0 }<br>
 *<br>
 * It will then be written to the file as:<br>
 * SmithNULLNULLNULLNULLNULL<br>
 *<br>
 * The class also provides methods for converting a record from file into an Address object by removing
 * any NULL padding at the end of field.<br>
 *<br>
 * Possible changes I plan to make in the future for additional practice with using both RandomAccessFiles
 * and linked lists are:<br>
 * <ol>
 *  <li>Implement methods to sort, search, and filter the file by individual field values</li>
 *  <li>Have the size of each field written at the beginning of the file so as to allow multiple sizes
 *   allowed by this class. For instance, if the first 10 characters of the file are:<br>
 *   6464400410<br>
 *   the size of each field would be double what they are currently. This would allow instantiation
 *   of an AddressBookFile to determine what size records it is working with instead of being limited
 *   to only allow one size.</li>
 *  <li>The ability to convert a file from one size to another</li>
 *  <li>The ability to construct an object from a file with delimiters rather than fixed size and convert
 *   a fixed size file to one that uses delimiters</li>
 * </ol>
 */
public class AddressBookFile {
    // bytes/max chars per field
    public static final Integer MAX_NAME_LENGTH = 32;
    public static final Integer MAX_STREET_LENGTH = 32;
    public static final Integer MAX_CITY_LENGTH = 20;
    public static final Integer MAX_STATE_LENGTH = 2;
    public static final Integer MAX_ZIP_LENGTH = 5;

    // total bytes/chars of an address
    public static final Integer TOTAL_ADDRESS_SIZE = (
        MAX_NAME_LENGTH + MAX_STREET_LENGTH + MAX_CITY_LENGTH + MAX_STATE_LENGTH + MAX_ZIP_LENGTH
    );

    // for storing the file to be worked with
    private File file;

    // pointer to the position of the first record in the file
    private Long firstRecordLocation;

    // pointer for traversing the file
    private Long currentSeekPosition;

    /**
     * Constructor for object
     * @param addressFile file for new/existing file containing files that adhere to this class
     * @throws IllegalArgumentException if an existing file is read only or a new file cannot be created
     */
    public AddressBookFile(File addressFile) throws IllegalArgumentException {
        file = addressFile;

        if (file.exists() && !file.canWrite()) {
            throw new IllegalArgumentException("Existing file cannot be accessed.");
        }
        else if (!file.exists()) {
            createNewAddressBookFile();
        }

        // this may be changed eventually if implementing the ability to have different lengths for fields
        firstRecordLocation = 0L;

        // set seek to beginning of file
        currentSeekPosition = firstRecordLocation;
    }

    /**
     * Constructor for creating object using a String for filename
     * @param fileName full filepath for new/existing file
     */
    public AddressBookFile(String fileName)  {
        this(new File(fileName));
    }

    /**
     * Creates a new file to work with if not already existing
     * @throws IllegalArgumentException if there is any issue creating the file
     */
    private void createNewAddressBookFile() throws IllegalArgumentException {
        try {
            file.createNewFile();
        }
        catch (IOException e) {
            System.out.println("Error creating new file " + file.getAbsolutePath());
            throw new IllegalArgumentException("Invalid file path");
        }
    }

    /**
     * Determines if there is at least one record in the file
     * @return true if the file is large enough to store at least one record
     */
    public Boolean hasRecords() {
        return file.length() >= (firstRecordLocation + TOTAL_ADDRESS_SIZE);
    }

    /**
     * Moves the pointer to the first record in the file and gets it
     * @return first record in file
     * @throws NullPointerException if there are no records in the file
     */
    public Address getFirst() throws NullPointerException {
        if (file.length() > firstRecordLocation) {
            currentSeekPosition = firstRecordLocation;
        }
        else {
            throw new NullPointerException("No records currently exist in file.");
        }
        return getCurrent();
    }

    /**
     * Used to determine if the pointer is capable of moving backwards, ie not pointing at the
     * first record of the file
     * @return true if there are previous records
     */
    public Boolean hasPrevious() {
        return currentSeekPosition > firstRecordLocation;
    }

    /**
     * Moves the pointer back one record and returns it
     * Use hasPrevious() before calling this method for additional null pointer safety
     * @return the previous record in the file
     * @throws NullPointerException if already pointing to the first record in the file or no records exist
     */
    public Address getPrevious() throws NullPointerException {
        if (hasPrevious()) {
            currentSeekPosition -= TOTAL_ADDRESS_SIZE;
        }
        else {
            // already at beginning of file
            throw new NullPointerException("Currently at first record. No previous record exists.");
        }
        return getCurrent();
    }

    /**
     * Gets the current pointed to record.
     * The pointer should never be off track and not pointing to the beginning of a record but should
     * this ever happen somehow, the pointer will be reset to the first record in the file and this
     * method will return the first record (if there is one)
     * @return current record where pointer is in file.
     * @throws NullPointerException if there are no existing records or the pointer is at the end of the file
     */
    public Address getCurrent() throws NullPointerException {
        Address address = null;
        String accessMode = "r";
        try (RandomAccessFile raf = new RandomAccessFile(file, accessMode)) {
            // make sure we're at the beginning of an address record
            if (!((currentSeekPosition - firstRecordLocation) % TOTAL_ADDRESS_SIZE == 0)) {
                // this should NEVER happen but if it does it should be handled
                throw new StreamCorruptedException(
                    "Error navigating file. Current Seek Pointer is not pointing to the beginning of a record."
                );
            }

            // make sure we aren't currently pointing to the end of the file
            if (currentSeekPosition == file.length()) {
                throw new NullPointerException("No record found at record: " + currentSeekPosition/TOTAL_ADDRESS_SIZE);
            }

            // seek to current position
            raf.seek(currentSeekPosition);

            // create a byte array to hold the data
            byte[] byteArray = new byte[TOTAL_ADDRESS_SIZE];

            // read the data into the array
            raf.read(byteArray);

            // convert the data into an Address object
            address = convertByteArrayToAddress(byteArray);
        }
        catch (IOException e) {
            System.err.println("Exception occurred while retrieving current record.");
            System.err.println("Exception class: " + e.getClass());
            System.err.println("Exception message: " + e.getMessage());
            System.err.println("Stack trace: " + e.getStackTrace());

            // if the pointer position somehow magically got off track, reset it to the beginning of the file
            if (e.getClass().isInstance(StreamCorruptedException.class)) {
                System.err.println("Resetting pointer to beginning of file.");
                address = getFirst();
            }
        }

        return address;
    }

    /**
     * Used to determine if there are more records in the file after current pointer position
     * Use this before getNext() for null pointer safety to make sure you are not already
     * at the last record of the file
     * @return true if more records exist
     */
    public Boolean hasNext() {
        return currentSeekPosition < file.length() - TOTAL_ADDRESS_SIZE;
    }

    /**
     * Moves the pointer to the next record in the file and gets it
     * Use hasNext() before calling for additional null pointer safety
     * @return the next record in the file
     * @throws NullPointerException if already at last record (or no records exist)
     */
    public Address getNext() throws NullPointerException {
        if (hasNext()) {
            currentSeekPosition += TOTAL_ADDRESS_SIZE;
        }
        else {
            // already at end of file
            throw new NullPointerException("Currently at last record. No next record exists.");
        }
        return getCurrent();
    }

    /**
     * Moves the pointer to the last record in the file and gets it
     * @return  the last record in the file
     * @throws NullPointerException if there are no records in the file
     */
    public Address getLast() throws NullPointerException {
        if (file.length() > firstRecordLocation) {
            currentSeekPosition = file.length() - TOTAL_ADDRESS_SIZE;
        }
        else {
            throw new NullPointerException("No records currently exist in file.");
        }

        return getCurrent();
    }

    /**
     * Updates the currently pointed to record with a new Address object
     * Overwrites any data that may be present in that record currently.
     * @param address object to write to file
     */
    public void updateCurrent(Address address) {
        String accessMode = "rw";
        try (RandomAccessFile raf = new RandomAccessFile(file, accessMode)) {
            // file pointer to end of file
            raf.seek(currentSeekPosition);
            // write address
            raf.write(convertAddressToByteArray(address));
        }
        catch (IOException e) {
            System.err.println("Exception occurred while attempting to write new record.");
            System.err.println("Exception class: " + e.getClass());
            System.err.println("Exception message: " + e.getMessage());
            System.err.println("Stack trace: " + e.getStackTrace());
        }
    }

    /**
     * Adds a new Address record to the end of the file
     * @param address object to write to file
     */
    public void addNew(Address address) {
        // move current pointer to end of file which will point to the new address after adding it
        currentSeekPosition = file.length();
        // "update" the blank space in the file with the new address
        updateCurrent(address);
    }

    /**
     * @return the total number of records in the file
     */
    public int getNumRecords() {
        return (int) ((file.length() - firstRecordLocation) / TOTAL_ADDRESS_SIZE);
    }

    /**
     * Returns the entire AddressBookFile as an ArrayList of Address objects
     * Will return an empty list if there are no records yet
     * @return ArrayList of all Address records
     */
    public ArrayList<Address> getAllRecords() {
        ArrayList<Address> addresses = new ArrayList<>();
        // use a temp variable to capture the current pointer location
        long holdCurrentPosition = currentSeekPosition;
        currentSeekPosition = firstRecordLocation;

        // get all records until pointing at the end of the file
        while (currentSeekPosition != file.length()) {
            addresses.add(getCurrent());
            currentSeekPosition += TOTAL_ADDRESS_SIZE;
        }

        // reset the pointer to where it was before getting all records
        currentSeekPosition = holdCurrentPosition;

        return addresses;
    }

    /**
     * Converts an Address object into a byte array to write to file.
     * System.arraycopy copies a source array into a destination array
     * The parameters are the array to copy, where to start with the source,
     * the destination array, where to start in the destination array
     * and finally the length, or number of elements, to copy from the source
     * Each call to the function below gets a field from the Address object
     * and converts the field to a null padded char array in place
     *
     * It's ugly but it works.
     *
     * @param address address object to convert to byte array
     * @return  byte array of address object with each field padded with null characters
     */
    private byte[] convertAddressToByteArray(Address address) {
        // first convert the address to one char array
        char[] charArray = new char[TOTAL_ADDRESS_SIZE];
        final int sourcePos = 0;
        int destinationPos = 0;

        //

        // add name
        System.arraycopy(
            sizeStringToCharArray(address.getName(), MAX_NAME_LENGTH),
            sourcePos,
            charArray,
            destinationPos,
            MAX_NAME_LENGTH
        );
        // move destination array pointer
        destinationPos += MAX_NAME_LENGTH;

        // add street
        System.arraycopy(
            sizeStringToCharArray(address.getStreet(), MAX_STREET_LENGTH),
            sourcePos,
            charArray,
            destinationPos,
            MAX_STREET_LENGTH
        );
        // move destination array pointer
        destinationPos += MAX_STREET_LENGTH;

        // add city
        System.arraycopy(
            sizeStringToCharArray(address.getCity(), MAX_CITY_LENGTH),
            sourcePos,
            charArray,
            destinationPos,
            MAX_CITY_LENGTH
        );
        // move destination array pointer forward
        destinationPos += MAX_CITY_LENGTH;

        // add state
        System.arraycopy(
            sizeStringToCharArray(address.getState(), MAX_STATE_LENGTH),
            sourcePos,
            charArray,
            destinationPos,
            MAX_STATE_LENGTH
        );
        // move destination array pointer
        destinationPos += MAX_STATE_LENGTH;

        // add zipcode
        System.arraycopy(
            sizeStringToCharArray(address.getZipcode(), MAX_ZIP_LENGTH),
            sourcePos,
            charArray,
            destinationPos,
            MAX_ZIP_LENGTH
        );

        // convert to a byte array
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }

        return byteArray;
    }

    /**
     * Converts a byte array to an Address object by converting the byte array into
     * a character array, then pulling each field from that one by one, removing null character
     * padding.
     * @param byteArray array of bytes read from a RandomAccessFile object
     * @return new Address object from file read
     */
    private Address convertByteArrayToAddress(byte[] byteArray) {
        // convert byte array to char array
        char[] charArray = new char[byteArray.length];
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) byteArray[i];
        }

        // traverse the char array pulling out each field as a string
        // get name
        int sourceStart = 0;
        int sourceEnd = MAX_NAME_LENGTH;
        String name = convertCharArrayToString(sliceArray(charArray, sourceStart, sourceEnd));

        // get street
        sourceStart = sourceEnd;
        sourceEnd += MAX_STREET_LENGTH;
        String street = convertCharArrayToString(sliceArray(charArray, sourceStart, sourceEnd));

        // get city
        sourceStart = sourceEnd;
        sourceEnd += MAX_CITY_LENGTH;
        String city = convertCharArrayToString(sliceArray(charArray, sourceStart, sourceEnd));

        // get state
        sourceStart = sourceEnd;
        sourceEnd += MAX_STATE_LENGTH;
        String state = convertCharArrayToString(sliceArray(charArray, sourceStart, sourceEnd));

        // get zip
        sourceStart = sourceEnd;
        sourceEnd += MAX_ZIP_LENGTH;
        String zip = convertCharArrayToString(sliceArray(charArray, sourceStart, sourceEnd));

        // return a new Address from fields pulled
        return new Address(name, street, city, state, zip);
    }

    /**
     * Helper function
     * Takes a source char array and then returns a specific subsection of that array
     *
     * For example, if you have an array, sourceArray = {'a', 'b', 'c', 'd'}
     * and want an array consisting of only {'b', 'c'},
     * you would call this function like so:
     * sliceArray(sourceArray, 1, 3)
     *
     * Works similar to String.substring() but for a c-string type array
     *
     * @param sourceArray char array to slice
     * @param start starting index desired for subsection
     * @param stop ending index desired for subsection
     * @return a selected size of another array
     */
    private char[] sliceArray(char[] sourceArray, int start, int stop) {
        char[] returnArray = new char[stop-start];

        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = sourceArray[start+i];
        }

        return returnArray;
    }

    /**
     * Helper function
     * Converts a string to a char array of an exact size by either:
     * 1. Returning the string as a char array (if same size)
     * 2. Returning the string up to the size of the array (if longer than array size)
     * 3. Returning the string with following null characters (if shorter than array size)
     * Used for writing exact string lengths to random access file
     * @param str String to convert
     * @param arraySize Size of the resulting array
     * @return Char array of string to exact size with null characters if necessary
     */
    private char[] sizeStringToCharArray(String str, Integer arraySize) {
        // create a char array with each element auto-initialized to null
        char[] charArr = new char[arraySize];

        if (str.length() == arraySize) { // array = entire string
            charArr = str.toCharArray();
        }
        else if (str.length() > arraySize) { // slice string to match size
            charArr = str.substring(0, arraySize).toCharArray();
        }
        else { // populate elements of array with string character, leaving null characters at end
            for (int i = 0; i < str.length(); i++) {
                charArr[i] = str.charAt(i);
            }
        }

        return charArr;
    }

    /**
     * Helper function
     * Converts a char array with trailing null characters to a string without null characters
     * @param arr char array to convert
     * @return char array as string with any null characters trimmed
     */
    private String convertCharArrayToString(char[] arr) {
        StringBuilder str = new StringBuilder();
        for (char c : arr) {
            if (c != '\0') {
                str.append(c);
            }
            else {
                break;
            }
        }

        return str.toString();
    }
}
