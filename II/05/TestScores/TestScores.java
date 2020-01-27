package testscores;

public class TestScores {
    Double[] scores;

    public TestScores(Double[] arr) throws InvalidTestScore {
        this.scores = new Double[arr.length];

        for (int i = 0; i < arr.length; i++) {
            this.scores[i] = arr[i];
            if (arr[i] < 0 || arr[i] > 100) {
                throw new InvalidTestScore(arr[i]);
            }
        }
    }

    public double getAverage() {
        double total = 0;
        for (int i = 0; i < this.scores.length; i++) {
            total += this.scores[i];
        }

        return (total / this.scores.length);
    }

}
