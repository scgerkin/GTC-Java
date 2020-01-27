package uppercasefileconverter;

public class Main {

    public static void main(String[] args) {

        FileConverter test = new FileConverter();

        test.openFiles();
        test.convertFileToUppercase();

        System.exit(0);
    }
}
