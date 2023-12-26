package pvc.caracol.cinad.utils;

import pvc.caracol.cinad.analizador.lexico.tokens.enums.CharType;

public class PodarTextUtil {
    public static String podar(String text) {
        text = trimLeadingCharacterPodables(text);
        text = trimTrailingCharacterPodables(text);
        return text;
    }

    private static String trimLeadingCharacterPodables(String text) {
        int startIndex = 0;
        int length = text.length();

        while (startIndex < length && isCharacterPodable(text.charAt(startIndex))) {
            startIndex++;
        }

        return (startIndex < length) ? text.substring(startIndex) : "";
    }

    private static String trimTrailingCharacterPodables(String text) {
        int endIndex = text.length() - 1;

        while (endIndex >= 0 && isCharacterPodable(text.charAt(endIndex))) {
            endIndex--;
        }

        return (endIndex >= 0) ? text.substring(0, endIndex + 1) : "";
    }

    private static Boolean isCharacterPodable(Character character) {
        try {
            return (!Character.isDigit(character) &&
                    !Character.isAlphabetic(character) &&
                    (character.equals(CharType.ASTERISCO.getCharacter()) ||
                    character.equals(CharType.SPACE.getCharacter())));
        } catch (Exception ex) {
            return false;
        }
    }
}
