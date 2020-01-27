package uppercasefileconverter;

import java.util.Scanner;
import java.io.*;


public class FileConverter {

    private static final Scanner kb = new Scanner(System.in);

    private File inputFile;
    private File outputFile;
    private FileReader reader;
    private FileWriter writer;

    /**
     * Opens files for reading and creates FileReader and FileWriter objects
     * for processing the files
     */
    public void openFiles() {
        getFileLocs();
        createFileReader();
        createFileWriter();
    }

    /**
     * Gets file locations
     */
    private void getFileLocs() {
        getInputFile();
        getOutputFile();
    }

    /**
     * Gets input from user for location of this.inputFile
     */
    private void getInputFile() {
        String inputLoc = getInput("Enter the filepath for the input file: ");

        this.inputFile = new File(inputLoc);

        createFileReader();
    }

    /**
     * Gets input from user for location of this.outputFile
     */
    private void getOutputFile() {
        String outputLoc = getInput("Enter the filepath for the output file: ");

        File file = new File(outputLoc);

        if (file.exists() && !overwriteExistingFile()) {
            getOutputFile();
        }

        this.outputFile = file;
    }

    /**
     * Creates FileReader object for reading input from file and handles
     * exceptions
     */
    private void createFileReader() {
        try {
            this.reader = new FileReader(this.inputFile);
        }
        catch (FileNotFoundException e) {
            fileNotFound(this.inputFile.getPath());
        }
    }

    /**
     * Creates a FileWriter object for writing to file and handles exceptions
     */
    private void createFileWriter() {
        try {
            this.writer = new FileWriter(this.outputFile);
        }
        catch (IOException e) {
            unexpectedException(e);
        }
    }

    /**
     * Converts this.inputFile characters to uppercase for Latin alphabet a-z
     * and writes each character to this.outputFile
     */
    public void convertFileToUppercase() {
        int ch; // holds unicode integer values for characters

        try {
            while (true) {
                // get character
                ch = readChar();

                // convert character
                ch = convertCharacterToUppercase(ch);

                // write character
                writeChar(ch);
            }
        }
        catch (EOFException e) {
            // close files when EOF is reach for input
            closeFiles();
        }
        finally {
            System.out.println("File conversion complete.");
        }
    }

    /**
     * Gets next character from file
     * @return character as unicode value
     * @throws EOFException when EOF is reached or other exception occurs
     */
    private int readChar() throws EOFException {
        int readChar;

        try {
            readChar = this.reader.read();
        }
        catch (IOException e) {
            readChar = -1;
        }

        if (readChar == -1) {
            throw new EOFException();
        }
        else {
            return readChar;
        }
    }

    /**
     * Converts a unicode value from lowercase to uppercase
     * Only works for Latin alphabet a-z
     * @param ch
     * @return
     */
    private int convertCharacterToUppercase(int ch) {
        return (ch >= 97 && ch <= 122) ? ch-32 : ch;
    }

    /**
     * Writes unicode character to file
     * @param writeChar
     */
    private void writeChar(int writeChar) {
        try {
            this.writer.write(writeChar);
        }
        catch (IOException e) {
            unexpectedException(e);
        }
    }

    /**
     * Handles closing files
     */
    private void closeFiles() {
        try {
            this.reader.close();
            this.writer.close();
        }
        catch (IOException e) {
            unexpectedException(e);
        }

    }

    /**
     * Gets user input
     * @param msg out message to user
     * @return  user input as string
     */
    private String getInput(String msg) {
        System.out.print(msg);
        return kb.nextLine();
    }

    /**
     * Method to check for Y/N input from user
     * @param query Question to ask user
     * @return  True if Yes
     */
    private Boolean checkYorN(String query) {
        return (getInput(query + " (Y/N): ").equalsIgnoreCase("y"));
    }

    /**
     * Recovers from FileNotFound exceptions
     * @param filePath  the file path attempted
     */
    private void fileNotFound(String filePath) {
        String outMsg = "ERROR: File '" + filePath + "' not found.\n"
                      + "Would you like to enter a new file?";

        if (checkYorN(outMsg)) {
            getInputFile();
        }
        else {
            createEmptyFile();
        }
    }

    /**
     * Handles id-10t errors
     * Creates a new empty file from user input
     * This method is only called if the user inputs an invalid file path
     * and elects not to enter a new (valid) filepath
     */
    private void createEmptyFile() {
        FileWriter temp;
        try {
            // create the file and then close it
            temp = new FileWriter(this.inputFile);
            temp.close();
        }
        catch (IOException e) {
            unexpectedException(e);
        }
        finally {
            System.out.println(
                "Created file: " + this.inputFile.getPath() + "\n"
                + "OUTPUT FILE WILL BE EMPTY!"
            );
        }
    }

    /**
     * Prints exception message and stack trace for unusual exceptions
     * @param e caught exception
     */
    private void unexpectedException(Exception e) {
        System.out.println(
            "Unexpected exception: " + e.getMessage() + "\n"
            + "On stack: " + e.getStackTrace()
        );
    }

    /**
     * Checks if user wants to overwrite an already existing file for output
     * @return True if overwrite is yes
     */
    private Boolean overwriteExistingFile() {
        String outMsg = "File already exists. It will be overwritten.\n"
                      + "WARNING: This cannot be undone!\n"
                      + "Continue?";
        return checkYorN(outMsg);
    }

    /**
     * toString override returns the absolute file paths
     */
    @Override
    public String toString() {
        return (
            "IN-File Path: " + this.inputFile.getAbsolutePath() + "\n"
            + "OUT-File Path: " + this.outputFile.getAbsolutePath()
        );
    }

    /**
     * Finalize closes files in case they were not already closed
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        closeFiles();
    }
}
