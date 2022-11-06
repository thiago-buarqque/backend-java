package com.entropy.exceptions;

public class DefaultException extends Exception {
    public DefaultException(String message, int line, int column) {
        super(message + " at " + line + ":" + column);
    }
}
