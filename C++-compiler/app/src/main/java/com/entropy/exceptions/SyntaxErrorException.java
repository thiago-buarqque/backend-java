package com.entropy.exceptions;

import com.entropy.common.Tag;

public class SyntaxErrorException extends Exception{
    public SyntaxErrorException(Tag expectedTag, int column, int line,
                                Tag unexpectedTag) {
        super("Unexpected \"" + unexpectedTag + "\". Expected \"" +
                expectedTag + "\" at " + line + ":" + column + ".");
    }
}
