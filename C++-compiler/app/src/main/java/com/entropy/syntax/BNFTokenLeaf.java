package com.entropy.syntax;

import com.entropy.common.Tag;
import com.entropy.common.Token;
import com.entropy.exceptions.SyntaxErrorException;

import java.util.Collections;
import java.util.List;

public class BNFTokenLeaf extends BNFToken {
    public BNFTokenLeaf(String name, String value, Tag tag) throws Exception {
        super(name, value, tag);
    }

    @Override
    public void validate() throws Exception {
        lexicalAnalyzer.saveCurrentPosition();

        try {
            Token token = lexicalAnalyzer.getNextToken();

            if (getTag() != token.getTag()) {
                throw new SyntaxErrorException(
                        getTag(), token.getColumnStart(), token.getLine(),
                        token.getTag()
                );
            }
            System.out.println(getName() + " " + token.getValue());
            lexicalAnalyzer.removeLastSavedPosition();
        } catch (Exception exception) {
            lexicalAnalyzer.returnToLastSavedPosition();
            throw exception;
        }
    }

    @Override
    public List<Tag> getFirstTokenTag() {
        return Collections.singletonList(getTag());
    }
}
