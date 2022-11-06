package com.entropy.exceptions;

public class UnexpectedCharacterException extends Exception{
    public UnexpectedCharacterException(
            Character character, int column, int line) {
        
        System.err.println("Lexical error: unexpected character '" +
                character + "' at " + line + ":" + column);
    }
}
