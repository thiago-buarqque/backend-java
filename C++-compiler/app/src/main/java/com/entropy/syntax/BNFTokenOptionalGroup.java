package com.entropy.syntax;

import com.entropy.common.Tag;
import com.entropy.common.Token;
import com.entropy.exceptions.SyntaxErrorException;

import java.util.ArrayList;
import java.util.List;

public class BNFTokenOptionalGroup extends BNFTokenImpl {
    public BNFTokenOptionalGroup(String name, String value) throws Exception {
        super(name, value);
    }

    @Override
    public void validate() throws Exception {
        lexicalAnalyzer.saveCurrentPosition();

        Token currentToken = getCurrentToken();

        List<Tag> firstTokenTag1 = getFirstTokenTag();
        if(firstTokenTag1.contains(currentToken.getTag())) {
            for (int i = 0; i < tokens.size(); i++) {
                BNFToken bnfToken1 = tokens.get(i);

                List<Tag> firstTokenTag2 = bnfToken1.getFirstTokenTag();
                if(!firstTokenTag2.contains(currentToken.getTag())) {
                    continue;
                }
                try {
                    bnfToken1.validate();

                    lexicalAnalyzer.removeLastSavedPosition();

                    return;
                }
                catch (SyntaxErrorException syntaxErrorException) {
                    if(_currentTokenDoesNotMatchAny(currentToken, i)) {
                        throw syntaxErrorException;
                    }
                }
                catch (Exception ignored) {
                }
            }
        }

        lexicalAnalyzer.returnToLastSavedPosition();

        throw new Exception("Expected one of these tokens " + tokens);
    }

    private boolean _currentTokenDoesNotMatchAny(Token currentToken, int i) {
        for(int j = i; j < tokens.size(); j++) {
            BNFToken bnfToken2 = tokens.get(j);
            if (bnfToken2.getFirstTokenTag().contains(
                    currentToken.getTag())
            ) {
                return false;
            }
        }

        return true;
    }

    @Override
    public List<Tag> getFirstTokenTag() {
        List<Tag> tags = new ArrayList<>();

        for(BNFToken token : tokens) {
            tags.addAll(token.getFirstTokenTag());
        }

        return tags;
    }

}
