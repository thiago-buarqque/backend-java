package com.entropy.lexical;

import com.entropy.common.Tag;

import java.util.Hashtable;

public class TokenHelper {
    private static final Hashtable<String, Tag> FINAL_TOKENS =
            new Hashtable<String, Tag>()
    {
        {
            put("+", Tag.ADDITION);
            put("&&", Tag.AND);
            put("=", Tag.ATTRIBUTION);
            put("cin", Tag.CIN);
            put("}", Tag.CLOSE_CURLY_BRACKET);
            put(")", Tag.CLOSE_PARENTHESIS);
            put("]", Tag.CLOSE_SQUARED_BRACKET);
            put(":", Tag.COLON);
            put(",", Tag.COMMA);
            put("cout", Tag.COUT);
            put("!=", Tag.DIFFERENCE);
            put("/", Tag.DIVISION);
            put(".", Tag.DOT);
            put("\"", Tag.DOUBLE_QUOTES);
            put("else", Tag.ELSE);
            put("==", Tag.EQUALS);
            put("false", Tag.FALSE);
            put("for", Tag.FOR);
            put(">=", Tag.GREATER_THAN_EQUAL);
            put(">", Tag.GREATER_THAN);
            put("identifier", Tag.IDENTIFIER);
            put("if", Tag.IF);
            put("0-9", Tag.INT_NUMBER); // Only used on BNF
            put("int", Tag.INT);
            put("length", Tag.LENGTH);
            put("<=", Tag.LESS_THAN_EQUAL);
            put("<", Tag.LESS_THAN);
            put("main", Tag.MAIN);
            put("%", Tag.MODULE);
            put("*", Tag.MULTIPLICATION);
            put("{", Tag.OPEN_CURLY_BRACKET);
            put("(", Tag.OPEN_PARENTHESIS);
            put("[", Tag.OPEN_SQUARED_BRACKET);
            put("||", Tag.OR);
            put("return", Tag.RETURN);
            put(";", Tag.SEMICOLON);
            put("'", Tag.SINGLE_QUOTE);
            put("std", Tag.STD);
            put("std::string", Tag.STD_STRING); // Only used on BNF
            put("a-z0-9", Tag.STRING_CONTENT); // Only used on BNF
            put("string", Tag.STRING);
            put("-", Tag.SUBTRACTION);
            put("true", Tag.TRUE);
            put("_", Tag.UNDERSCORE);
            put("while", Tag.WHILE);
        }
    };

    public static Tag getFinalTokenTag(String tokenValue) {
        return FINAL_TOKENS.get(tokenValue);
    }

}
