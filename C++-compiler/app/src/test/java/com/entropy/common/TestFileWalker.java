package com.entropy.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.StringIndexOutOfBoundsException;

import com.entropy.helper.constants.CommonTestConstants;

public class TestFileWalker {

    FileWalker fileWalker;
    String path;

    @BeforeEach
    void setUp() {
        try {
            fileWalker =  FileWalker.getNewInstance(CommonTestConstants.FILE_PATH_CONSTANT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInitialCharacterIsNull() {
        Assertions.assertThrows(StringIndexOutOfBoundsException.class, fileWalker::getCurrentChar);
    }

    @Test
    void testFistCharacterIsPresent() {
        Character character = fileWalker.getNextChar();
        Assertions.assertEquals('i', character);
    }

    @Test
    void testCurrentCharAfterFirstCharacter() {
        fileWalker.getNextChar();
        Character character = fileWalker.getCurrentChar();
        Assertions.assertEquals('i', character);
    }

    @Test
    void testCurrentCharAfterGoBackFisrtCharacter() {
        fileWalker.getNextChar();
        fileWalker.goBackOneChar();
        Character character = fileWalker.getCurrentChar();
        Assertions.assertEquals('i', character);
    }

    @Test
    void testCurrentCharAfterGoBackAnyCharacter() {
        fileWalker.getNextChar();
        fileWalker.getNextChar();
        fileWalker.goBackOneChar();
        Character character = fileWalker.getCurrentChar();
        Assertions.assertEquals('i', character);
    }
}
