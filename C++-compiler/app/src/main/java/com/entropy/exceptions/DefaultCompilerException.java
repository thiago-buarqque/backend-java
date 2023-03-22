package com.entropy.exceptions;

public class DefaultCompilerException extends Exception {
    public DefaultCompilerException(String message, int line, int column) {
        super(message + " at " + line + ":" + column);
    }
}
