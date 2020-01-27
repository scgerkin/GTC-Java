package exoticmoves;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * InputValidator class is an abstract utility class to provide input validation
 */
public abstract class InputValidator {

    private InputValidator() {} // utility class: cannot be instantiated

    public static Boolean onlyLetters(String strToCheck,
                                      Boolean allowWhiteSpace)
    {
        if (allowWhiteSpace) {
            strToCheck = removeWhiteSpace(strToCheck);
        }
        return strToCheck.matches("^[a-zA-Z]+$");
    }

    public static Boolean yearMonthNotBeforeNow(Integer year, Integer month) {
        LocalDate currentDate = LocalDate.now();
        YearMonth input = YearMonth.of(year, month);
        YearMonth current =
            YearMonth.of(currentDate.getYear(), currentDate.getMonthValue());

        return !input.isBefore(current);
    }

    // allows whitespace
    public static Boolean onlyDigits(String strToCheck,
                                     Boolean allowWhiteSpace)
    {
        if (allowWhiteSpace) {
            strToCheck = removeWhiteSpace(strToCheck);
        }
        return strToCheck.matches("^[0-9]+$");
    }

    // overload
    public static Boolean onlyDigits(String strToCheck, Integer totalDigits,
                                     Boolean allowWhiteSpace)
    {
        if (allowWhiteSpace) {
            strToCheck = removeWhiteSpace(strToCheck);
        }
        String regex = "^[0-9]{" + totalDigits + "}$";
        return strToCheck.matches(regex);
    }

    private static String removeWhiteSpace(String str) {
        return str.replaceAll("\\s", "");
    }
}
