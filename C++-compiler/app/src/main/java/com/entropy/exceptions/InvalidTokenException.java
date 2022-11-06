package com.entropy.exceptions;

public class InvalidTokenException extends Exception{
    public InvalidTokenException(String tokenValue, int line, int column) {
        super("Lexical error: invalid token \"" + tokenValue + "\" at " +
                line + ":" + column);
    }
}
