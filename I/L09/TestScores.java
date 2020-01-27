


package lesson9lab8program1;

/**
 *  TestScores class creates an object with three fields for test scores
 */
public class TestScores {
    
    private double test1;
    private double test2;
    private double test3;
    
    /**
     * No-argument constructor sets all tests to 0
     */
    public TestScores() {
        test1 = 0;
        test2 = 0;
        test3 = 0;
    }
    
    /**
     * Constructor to create an object with all fields set to a value
     * @param num1  Value for test 1
     * @param num2  Value for test 2
     * @param num3  Value for test 3
     */
    public TestScores(double num1, double num2, double num3) {
        test1 = num1;
        test2 = num2;
        test3 = num3;
    }
    
    /**
     * Accessor method to get a fields value
     * @param testNum   which field to get the value for
     * @return  returns the score for the selected test
     */
    public double getScore(int testNum) {
    
        switch (testNum) {
            case 1:
                return test1;
            case 2:
                return test2;
            case 3:
                return test3;
            default:
                return 0;
        }
    }
    
    /**
     * Mutator determines which test to change and changes the appropriate field
     * @param testNum   Which test field to change
     * @param testScore Value to change for a field
     */
    public void setScore(int testNum, double testScore) {
        
        switch (testNum) {
            case 1:
                test1 = testScore;
                break;
            case 2:
                test2 = testScore;
                break;
            case 3:
                test3 = testScore;
                break;
            default:
                break;
        }
    }
    
    /**
     * 
     * @return average of all test scores
     */
    public double calcAverage() {
        return (test1 + test2 + test3) / 3;
    }
}