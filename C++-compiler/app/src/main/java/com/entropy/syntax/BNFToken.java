package com.entropy.syntax;

import com.entropy.common.Tag;
import com.entropy.common.Token;
import com.entropy.lexical.LexicalAnalyzer;

import java.util.List;

public abstract class BNFToken {
    protected LexicalAnalyzer lexicalAnalyzer;
    private final String _name;
    private final Tag _tag;
    private final String _value;

    public BNFToken(String name, String value, Tag tag) throws Exception {
        if(name.equals("")) {
            name = "anonymous";
        }
        _name = name;
        _tag = tag;
        _value = value;

        lexicalAnalyzer = LexicalAnalyzer.getInstance();
    }

    public abstract void validate() throws Exception;

    public abstract List<Tag> getFirstTokenTag();

    public Token getCurrentToken() {
        return lexicalAnalyzer.getCurrentToken();
    }

    public String getName() {
        return _name;
    }

    public String getValue() {
        return _value;
    }

    public Tag getTag() {
        return _tag;
    }

    @Override
    public String toString() {
        return _name;
    }
}
