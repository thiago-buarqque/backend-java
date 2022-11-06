package com.entropy.common;

public enum Tag {
    ADDITION("ADDITION"),
    AND("AND"),
    ATTRIBUTION("ATTRIBUTION"),
    CIN("CIN"),
    CLOSE_CURLY_BRACKET("CLOSE_CURLY_BRACKET"),
    CLOSE_PARENTHESIS("CLOSE_PARENTHESIS"),
    CLOSE_SQUARED_BRACKET("CLOSE_SQUARED_BRACKET"),
    COLON("COLON"),
    COMMA("COMMA"),
    COUT("COUT"),
    DIFFERENCE("DIFFERENCE"),
    DIVISION("DIVISION"),
    DOT("DOT"),
    DOUBLE_COLON("DOUBLE_COLON"),
    DOUBLE_QUOTES("DOUBLE_QUOTES"),
    ELSE("ELSE"),
    EQUALS("EQUALS"),
    FALSE("FALSE"),
    FOR("FOR"),
    GREATER_THAN("GREATER_THAN"),
    GREATER_THAN_EQUAL("GREATER_THAN_EQUAL"),
    IDENTIFIER("IDENTIFIER"),
    IF("IF"),
    INT("INT"),
    INT_NUMBER("INT_NUMBER"),
    LENGTH("LENGTH"),
    LESS_THAN("LESS_THAN"),
    LESS_THAN_EQUAL("LESS_THAN_EQUAL"),
    MAIN("MAIN"),
    MODULE("MODULE"),
    MULTIPLICATION("MULTIPLICATION"),
    OPEN_CURLY_BRACKET("OPEN_CURLY_BRACKET"),
    OPEN_PARENTHESIS("OPEN_PARENTHESIS"),
    OPEN_SQUARED_BRACKET("OPEN_SQUARED_BRACKET"),
    OR("OR"),
    RETURN("RETURN"),
    SEMICOLON("SEMICOLON"),
    SINGLE_QUOTE("SINGLE_QUOTE"),
    STD("STD"),
    STD_STRING("STD::STRING"),
    STRING("STRING"),
    STRING_CONTENT("STRING_CONTENT"),
    SUBTRACTION("SUBTRACTION"),
    TRUE("TRUE"),
    UNDERSCORE("UNDERSCORE"),
    WHILE("WHILE");

    private final String description;

    Tag(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
