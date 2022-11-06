package com.entropy.syntax;

import com.entropy.common.Tag;
import com.entropy.common.Token;

import java.util.ArrayList;
import java.util.List;

public class BNFTokenImpl extends BNFToken {
    protected final List<BNFToken> tokens = new ArrayList<>();
    protected String _tokenName;

    public BNFTokenImpl(String name, String value) throws Exception {
        super(name, value, null);
        _tokenName = name;
    }

    @Override
    public void validate() throws Exception {
        lexicalAnalyzer.saveCurrentPosition();

        testFirstToken();

        for (BNFToken bnfToken : tokens) {
            try {
                bnfToken.validate();
            } catch (BNFTokenOptional.BNFTokenOptionalException ignored) {
            }
            catch (Exception exception) {
                lexicalAnalyzer.returnToLastSavedPosition();
                throw exception;
            }
        }

        lexicalAnalyzer.removeLastSavedPosition();
    }

    protected void testFirstToken() throws Exception {
        Token currentToken = getCurrentToken();
        Tag currentTokenTag = currentToken.getTag();

        BNFToken firstToken = tokens.get(0);

        List<Tag> firstTokenTag = firstToken.getFirstTokenTag();

        if(!firstTokenTag.contains(currentTokenTag)) {
            throw new Exception(String.format("%s is not equals to first " +
                    "token of %s", currentTokenTag.name(), firstTokenTag));
        }
    }

    @Override
    public List<Tag> getFirstTokenTag() {
        return tokens.get(0).getFirstTokenTag();
    }

    public void addToken(BNFToken bnfToken) {
        tokens.add(bnfToken);
    }

    public void removeToken(BNFToken bnfToken) {
        tokens.remove(bnfToken);
    }

    public String getTokenName() {
        return _tokenName;
    }
}
