package com.entropy.common;

public class Token {
    private int _columnEnd;
    private int _columnStart;
    private int _line;
    private int _scope;
    private Tag _tag;
    private String _value;

    public Token() {
        _value = "";
    }

    public Token(Tag tag, String value) {
        _tag = tag;
        _value = value;
    }

    public Token(String value) {
        _value = value;
    }

    public int getColumnEnd() {
        return _columnEnd;
    }

    public void setColumnEnd(int columnEnd) {
        _columnEnd = columnEnd;
    }

    public int getColumnStart() {
        return _columnStart;
    }

    public void setColumnStart(int columnStart) {
        _columnStart = columnStart;
    }

    public int getLine() {
        return _line;
    }

    public void setLine(int line) {
        _line = line;
    }

    public int getScope() {
        return _scope;
    }

    public void setScope(int scope) {
        _scope = scope;
    }

    public Tag getTag() {
        return _tag;
    }

    public void setTag(Tag tag) {
        _tag = tag;
    }

    public String getValue() {
        return _value;
    }

    public void setValue(String value) {
        _value = value.trim();
    }

    public void appendValue(String value) {
        _value += value.trim();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Token) {
            Token token = (Token) object;

            return _value.equals(token.getValue());
        }

        return false;
    }

    @Override
    public String toString() {
        return "<" + _value + "> : " + _tag;
    }
}
