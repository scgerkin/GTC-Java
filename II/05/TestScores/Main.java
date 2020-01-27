package testscores;

/**
 * @author Stephen Gerkin
 * @date 2019-06-04
 * Programming Lab 06 - TestScores Class
 * Program to demonstrate an exception class named InvalidTestScore
 */
public class Main {

    /**
     * Main method demonstrates the TestScores class and InvalidTestScore
     * exception class
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Double[] scoreArray = {85.0, 95.0, 101.0};

        // show exception
        makeTestScores(scoreArray);

        // correct data entry error
        scoreArray[2] = 100.0;

        // show no exceptions
        makeTestScores(scoreArray);

        System.exit(0);
    }

    public static void makeTestScores(Double[] arr) {
        try {
            TestScores testScores = new TestScores(arr);
            System.out.println("Average: " + testScores.getAverage());
        }
        catch (InvalidTestScore e) {
            System.out.println(e.getMessage());
            System.out.println(
                "Valid test scores must be positive values below 100.0"
            );
        }
    }
}
