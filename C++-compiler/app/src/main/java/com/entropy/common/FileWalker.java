package com.entropy.common;

import com.entropy.lexical.CharacterHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileWalker {
    private int _currentColumn;
    private int _currentLine = 1;
    private final String _data;
    private final int _eofIndex;
    private static FileWalker _fileWalker;
    private int _index;
    private static String _lastFilePath;
    private int _lastLineColumns = 0;

    private FileWalker(String filePath) throws IOException {
        File file = new File(filePath);

        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] dataBytes = new byte[(int) file.length()];

        fileInputStream.read(dataBytes);
        fileInputStream.close();

        _data = new String(dataBytes, "UTF-8");
        Scanner _scanner = new Scanner(_data);
        _scanner.useDelimiter("[\r\n]+");
        _eofIndex = _data.length();

        _index = -1;
    }

    public static synchronized FileWalker getNewInstance(String filePath)
            throws IOException {

        if (_fileWalker == null ||
                (filePath != null && filePath.equals(_lastFilePath))) {

            _fileWalker = new FileWalker(filePath);
            _lastFilePath = filePath;
        }

        return _fileWalker;
    }

    public static synchronized FileWalker getInstance() throws IOException {
        return getNewInstance(null);
    }

    public Character getNextChar() {
        Character character = null;

        if (!isEOF()) {
            _index +=1;
            _currentColumn += 1;

            character = _data.charAt(_index);

            if(CharacterHelper.isBreakLine(character)) {
                _currentLine += 1;
                _lastLineColumns = _currentColumn;
                _currentColumn = 0;
            }
        }

        return character;
    }

    public Character getCurrentChar() {
        Character character = null;

        if (!isEOF()) {
            character = _data.charAt(_index);
        }

        return character;
    }

    public boolean isEOF() {
        return _index >= _eofIndex - 1;
    }

    public void goBackOneChar() {
        goBackNChars(1);
    }

    public void goBackNChars(int n) {
        _index -= n;
        _currentColumn -= n;

        if(_index < 0) {
            _index = -1;
            _currentColumn = -1;
        }

        Character character = getCurrentChar();
        if(character != null && CharacterHelper.isBreakLine(character)) {
            _currentColumn = _lastLineColumns;
        }
    }

    public int getCurrentLine() {
        return _currentLine;
    }

    public int getCurrentColumn() {
        return _currentColumn;
    }
}
