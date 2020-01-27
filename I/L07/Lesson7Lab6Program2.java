/*
    Name: Stephen Gerkin
    Date: 02/26/2019
    Lesson 7 Lab 6 Program 2
    Program Title:
        Uppercase File Converter
    Program Description:
        Program to get location of input file from user
        Convert text file to all uppercase
        Write to output file
 */

package lesson7lab6program2;

import java.util.Scanner;
import java.io.*;

/**
 * Class definition
 */
public class Lesson7Lab6Program2 {

    /**
     * Main method
     */
    public static void main(String[] args) throws IOException {
        Scanner kb = new Scanner(System.in);
        String outputMsg;
        
        // get input file name
        outputMsg = "Enter the name of the input file: ";
        System.out.print(outputMsg);
        String inputFileName = kb.nextLine();
        
        // open File for read
        File inFile = new File(inputFileName);
        
        // if file not found, prompt again until correct name entered
        while (!inFile.exists()) {
            inputFileName = InfileNotFound(inputFileName);
            
            inFile = new File(inputFileName);
        }
        
        // declare/init input file with name
        Scanner inputFile = new Scanner(inFile);
        
        // get output file name
        outputMsg = "Enter the name of the output file: ";
        System.out.print(outputMsg);
        String outputFileName = kb.nextLine();
        
        // open file for output
        File outFile = new File(outputFileName);
        
        // if file exists confirm overwrite or get new name
        if (outFile.exists()) {
            outputFileName = OutputExists(outputFileName);
            
            outFile = new File(outputFileName);
        }
        
        // declare/init output file with name
        PrintWriter outputFile = new PrintWriter(outFile);
        
        // write input file to output file after converting to upper case
        while (inputFile.hasNext()) {
            String line = inputFile.nextLine();
            
            line = line.toUpperCase();
            
            outputFile.println(line);
        }
        
        // close files
        inputFile.close();
        outputFile.close();

        // exit java VM
        System.exit(0);
    }
    
    /**
     * Name:
     *  InfileNotFound
     * Parameters:
     *  @param inputFileName    the input file name originally entered
     * Returns:
     *  @return new filename from user
     * Description:
     *  Lets user know that file name for input was not found
     *  Gets and returns new file name
     */
    public static String InfileNotFound(String inputFileName) {
        String outputMsg;
        Scanner kb = new Scanner(System.in);
        
        outputMsg = "Error: File " + inputFileName + " not found.";
        outputMsg += "\n";
        outputMsg += "Enter the name of the input file: ";
        System.out.println(outputMsg);
        inputFileName = kb.nextLine();
        
        return inputFileName;
    }
    
    /**
     * Name:
     *  OutputExists
     * Parameters:
     *  @param outputFileName   the name entered by user for output
     * Return:
     *  @return file name for output
     * Description:
     *  Lets user know the file already exists
     *  Asks if they want to overwrite the file
     *  If yes, returns the original file name
     *  If no, gets and returns new file name
     */
    public static String OutputExists(String outputFileName) {
        String outputMsg;
        char queryOverwrite;
        Scanner kb = new Scanner(System.in);
        
        outputMsg = "Error: File " + outputFileName + " already exists.";
        outputMsg += "\n";
        outputMsg += "Do you want to overwrite? (y/n): ";
        System.out.print(outputMsg);
        queryOverwrite = kb.nextLine().charAt(0);
        
        if (queryOverwrite == 'Y' | queryOverwrite == 'y') {
            return outputFileName;
        }
        else {
            outputMsg = "Enter the name of the output file: ";
            System.out.print(outputMsg);
            outputFileName = kb.nextLine();
            return outputFileName;
        }
    }
}
