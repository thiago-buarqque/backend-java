package com.entropy.exceptions;

public class UnexpectedEOFException extends Exception{
    public UnexpectedEOFException(int line, int column) {
        System.err.println("Lexical error: unexpected EOF at " + line +
                ":" + column);
    }
}
