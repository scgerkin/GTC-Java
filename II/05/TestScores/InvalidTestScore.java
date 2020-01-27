package testscores;

public class InvalidTestScore extends IllegalArgumentException {

    public InvalidTestScore() {
        super("Invalid Test Score entered. Tests must be in range: 0-100");
    }

    public InvalidTestScore(double score) {
        super("Invalid Test Score entered: " + score);
    }
}
