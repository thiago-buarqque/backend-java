package com.entropy.lexical;

public final class CharacterHelper {

    private static final String _validCharacters =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz<>=!\"[]{" +
                    "}(),;./+-*%|_:&\n\t\r0123456789 ";

    public static boolean isValidCharacter(Character character) {
        return _validCharacters.contains(character.toString());
    }

    public static boolean isDigit(Character character) {
        return  character.compareTo('0') >= 0 && character.compareTo('9') <= 0;
    }

    public static boolean isLetter(Character character) {
        return  character.compareTo('a') >= 0 &&
                character.compareTo('z') <= 0 ||
                character.compareTo('A') >= 0 && character.compareTo('Z') <= 0;
    }

    public static boolean isUnderscore(Character character) {
        return character.equals('_');
    }

    public static boolean isBreakLine(Character character) {
        return character.equals('\n');
    }
}
