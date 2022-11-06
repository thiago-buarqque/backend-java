package com.entropy.lexical;

import com.entropy.common.FileWalker;
import com.entropy.common.Tag;
import com.entropy.common.Token;
import com.entropy.exceptions.DefaultException;
import com.entropy.exceptions.UnexpectedCharacterException;
import com.entropy.exceptions.UnexpectedEOFException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LexicalAnalyzer {
    private final Stack<Integer> _backupColumnPosition;
    private Token _currentToken;
    private FileWalker _filewalker;
    private static LexicalAnalyzer _lexicalAnalyzer;
    private List<Token> _tokens;
    private int _tokenIndex = 0;

    private LexicalAnalyzer() throws Exception {
        _filewalker = FileWalker.getInstance();
        _backupColumnPosition = new Stack<>();
        _tokens = new ArrayList<>();
    }

    public void buildTokens() throws Exception {
        Token token = buildNextToken();

        while (token != null) {
            _tokens.add(token);

            token = buildNextToken();
        }
    }

    public static synchronized LexicalAnalyzer getInstance() throws Exception {

        if (_lexicalAnalyzer == null) {
            _lexicalAnalyzer = new LexicalAnalyzer();
        }

        return _lexicalAnalyzer;
    }

    public synchronized Token getNextToken() {
        _tokenIndex += 1;

        return _tokens.get(_tokenIndex - 1);
    }

    public synchronized Token getCurrentToken() {
        return _tokens.get(_tokenIndex);
    }

    public Token buildNextToken() throws Exception {
        _currentToken = new Token();

        Character character;
        do {
            character = _filewalker.getNextChar();

            if(character == null) {
                return null;
            }
        }
        while(character.equals('\n') || character.equals(' '));

        if(!CharacterHelper.isValidCharacter(character)) {
            throw new UnexpectedCharacterException(character,
                    _filewalker.getCurrentColumn(),
                    _filewalker.getCurrentLine()
            );
        }

        if (CharacterHelper.isUnderscore(character) ||
                CharacterHelper.isLetter(character)) {

            constructIdentifierToken(character);
        } else if (CharacterHelper.isDigit(character)) {
            _constructIntNumberToken(character);
        }
        else if(character.equals('\"')) {
            constructStringToken();
        }
        else {
            _constructFinalToken(character);
        }

        return _currentToken;
    }

    public void saveCurrentPosition() {
        _backupColumnPosition.push(_tokenIndex);
    }

    public void removeLastSavedPosition() {
        _backupColumnPosition.pop();
    }

    public void returnToLastSavedPosition() throws Exception {
        if(_backupColumnPosition.isEmpty()) {
            throw new Exception("Backup column stack is empty");
        }

        _tokenIndex = _backupColumnPosition.pop();
    }

    private boolean isCharPartOfIdentifierToken(Character character) {
        return CharacterHelper.isUnderscore(character) ||
                CharacterHelper.isLetter(character) ||
                CharacterHelper.isDigit(character);
    }

    private void constructIdentifierToken(Character character)
            throws Exception {

        _setTokenLineAndColumn();

        while (isCharPartOfIdentifierToken(character)) {

            character = _getNextCharacter(character);
        }

        Tag tag = TokenHelper.getFinalTokenTag(_currentToken.getValue());
        _filewalker.goBackOneChar();

        if(tag == null) {
            tag = Tag.IDENTIFIER;
        }

        _currentToken.setTag(tag);
        _setTokenColumnEnd();
    }

    private Character _getNextCharacter(Character character)
            throws UnexpectedEOFException, UnexpectedCharacterException {

        _appendTokenValue(character);

        character = _filewalker.getNextChar();

        if (character == null) {
            throw new UnexpectedEOFException(_filewalker.getCurrentLine(),
                    _filewalker.getCurrentColumn());
        }
        else if (!CharacterHelper.isValidCharacter(character)){
            throw new UnexpectedCharacterException(character,
                    _filewalker.getCurrentColumn(),
                    _filewalker.getCurrentLine()
            );
        }

        return character;
    }

    private void _constructIntNumberToken(Character character)
            throws Exception {

        _currentToken.setTag(Tag.INT_NUMBER);

        _setTokenLineAndColumn();

        while (CharacterHelper.isDigit(character)) {

            character = _getNextCharacter(character);
        }

        if (character.equals('.')) {
            throw new DefaultException("Floating numbers are not allowed",
                    _filewalker.getCurrentLine(),
                    _filewalker.getCurrentColumn());
        }

        _filewalker.goBackOneChar();

        _setTokenColumnEnd();
    }

    private void _constructFinalToken(Character character) {
        _setTokenLineAndColumn();

        Character nextCharacter = _filewalker.getNextChar();

        String tokenvalue = String.valueOf(character);

        Tag tag = null;

        if(nextCharacter != null) {
            tag = TokenHelper.getFinalTokenTag(
                    character + String.valueOf(nextCharacter)
            );

            if(tag != null) {
                tokenvalue += nextCharacter;
            } else {
                _filewalker.goBackOneChar();
            }
        }

        if(tag == null) {
            tag = TokenHelper.getFinalTokenTag(String.valueOf(character));
        }

        _appendTokenValue(tokenvalue);

        _currentToken.setTag(tag);
        _setTokenColumnEnd();
    }

    private void constructStringToken() throws Exception {
        _setTokenLineAndColumn();

        _appendTokenValue(_filewalker.getCurrentChar());
        Character character = _filewalker.getNextChar();
        while (!character.equals('\"')) {
            _appendTokenValue(character);

            character = _filewalker.getNextChar();

            if (character == null) {
                throw new UnexpectedEOFException(_filewalker.getCurrentLine(),
                        _filewalker.getCurrentColumn());
            }
        }

        _appendTokenValue(character);

        _currentToken.setTag(Tag.STRING_CONTENT);
        _setTokenColumnEnd();
    }

    private void _setTokenColumnEnd() {
        _currentToken.setColumnEnd(_filewalker.getCurrentColumn());
    }

    private void _setTokenLineAndColumn() {
        _currentToken.setLine(_filewalker.getCurrentLine());
        _currentToken.setColumnStart(_filewalker.getCurrentColumn());
    }

    private void _appendTokenValue(Character character) {
        _currentToken.appendValue(String.valueOf(character));
    }

    private void _appendTokenValue(String character) {
        _currentToken.appendValue(character);
    }
}
