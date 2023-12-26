package pvc.caracol.cinad.analizador.lexico.tokens.utils;

public class WordsUtil {
    public static Boolean isInteger(String word) {
        try {
            Integer.parseInt(word);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Boolean isDouble(String word) {
        try {
            Double.parseDouble(word);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Boolean isAllDigits(String word) {
        return word.chars().allMatch(Character::isDigit);
    }
}
