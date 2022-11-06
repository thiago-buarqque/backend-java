package com.entropy.syntax;

import com.entropy.exceptions.SyntaxErrorException;

public class BNFTokenOptional extends BNFTokenImpl {

    private boolean _repeats = false;

    public BNFTokenOptional(String name, String value) throws Exception {
        super(name, value);
    }

    public BNFTokenOptional(String name, String value, boolean repeats)
            throws Exception {

        super(name, value);

        _repeats = repeats;
    }

    @Override
    public void validate() throws Exception {
        int n = 1;
        if (_repeats) {
            n = Integer.MAX_VALUE;
        }

        while (n > 0) {
            lexicalAnalyzer.saveCurrentPosition();
            try {
                testFirstToken();
            } catch (Exception exception) {
                lexicalAnalyzer.removeLastSavedPosition();
                throw new BNFTokenOptionalException();
            }

            for (BNFToken bnfToken : tokens) {
                try {
                    bnfToken.validate();
                }
                catch (SyntaxErrorException exception) {
                    lexicalAnalyzer.returnToLastSavedPosition();
                    throw exception;
                }
                catch (Exception exception) {
                    lexicalAnalyzer.returnToLastSavedPosition();
                    throw new BNFTokenOptionalException();
                }
            }

            lexicalAnalyzer.removeLastSavedPosition();
            n--;
        }
    }

    public static class BNFTokenOptionalException extends Exception {
    }
}
