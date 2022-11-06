package com.entropy.syntax;

import com.entropy.lexical.TokenHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class SyntaxBuilder {

    private final Map<String, BNFTokenLeaf> _finalTokens = new Hashtable<>();
    private Scanner _scanner;
    private final Map<String, BNFTokenImpl> _tokens = new Hashtable<>();

    private final String _nonFinalTokensFilePath = System.getProperty("user" +
            ".dir") + "/app/src/main/resources/bnf_tokens.txt";

    private final String finalTokensFilePath =
            System.getProperty("user.dir") + "/app/src/main/resources" +
                    "/bnf_finals.txt";

    public BNFTokenOptionalGroup readBNF() throws Exception {
        _readFile(finalTokensFilePath);

        _getFinalTokens();

        _readFile(_nonFinalTokensFilePath);

        _getNonFinalTokens();

        BNFTokenOptionalGroup bnfToken =
                new BNFTokenOptionalGroup("BNF","BNF");

        for(BNFToken bnfToken1 : _tokens.values()) {
            bnfToken.addToken(bnfToken1);
        }

        return bnfToken;
    }

    private void _getFinalTokens() throws Exception {
        while (_scanner.hasNextLine()) {
            String nextLine = _scanner.nextLine();
            if(nextLine.trim().equals("")) {
                continue;
            }

            String[] token = nextLine.split(" ::= ");

            String tokenName = token[0];
            String tokenValue = token[1];

            BNFTokenLeaf bnfToken = new BNFTokenLeaf(tokenName, tokenValue,
                    TokenHelper.getFinalTokenTag(tokenValue));

            _finalTokens.put(bnfToken.getName(), bnfToken);
        }
    }

    private void _getNonFinalTokens() throws Exception {
        _getNonFinalTokensName();

        _readFile(_nonFinalTokensFilePath);

        while (_scanner.hasNextLine()) {
            String nextLine = _scanner.nextLine();
            if (nextLine.trim().equals("")) {
                continue;
            }

            String[] token = nextLine.split(" ::= ");

            String tokenName = token[0];
            String tokenValue = token[1];

            BNFTokenImpl nonFinalToken = _tokens.get(tokenName);

            _getNonFinalTokenTokenList(nonFinalToken, tokenName,
                            tokenValue);
        }
    }

    private void _getNonFinalTokensName() throws Exception {
        while (_scanner.hasNextLine()) {
            String nextLine = _scanner.nextLine();
            if(nextLine.trim().equals("")) {
                continue;
            }

            String[] tokenData = nextLine.split(" ::= ");

            String tokenName = tokenData[0];
            String tokenValue = tokenData[1];

            BNFTokenImpl bnfToken = null;

            if (_isSequentialToken(tokenValue)) {
                bnfToken = new BNFTokenImpl(tokenName, tokenValue);
            }
            else if (_isConditionalToken(tokenValue)) {
                bnfToken = new BNFTokenOptionalGroup(tokenName, tokenValue);
            }
            else if (_isOptionalToken(tokenValue)) {
                bnfToken = new BNFTokenOptional(tokenName, tokenValue);
            }
            else if (_isRepeatToken(tokenValue)) {
                bnfToken = new BNFTokenOptional(tokenName, tokenValue, true);
            }
            else {
                throw new Exception("Invalid token type \"" + tokenName + "\"");
            }

            _tokens.putIfAbsent(tokenData[0], bnfToken);
        }
    }

    private void _getNonFinalTokenTokenList(
            BNFTokenImpl nonFinalToken, String tokenName, String tokenValue)
            throws Exception {

        BNFTokenImpl bnfToken = nonFinalToken;
        String splitter = " ";

        if (_isSequentialToken(tokenValue)) {
            splitter = " ";
        }
        else if (_isConditionalToken(tokenValue)) {
            splitter = " \\| ";
        }
        else if (_isOptionalToken(tokenValue) || _isRepeatToken(tokenValue)) {
            tokenValue = tokenValue.substring(1, tokenValue.length()-1);

            if (_isConditionalToken(tokenValue)) {
                splitter = " \\| ";
                bnfToken = new BNFTokenOptionalGroup(tokenName, tokenValue);
                nonFinalToken.addToken(bnfToken);
            }
        }

        _registerTokenTokenList(splitter, bnfToken, tokenValue);
    }

    private boolean _isConditionalToken(String value) {
        return !_isSequentialToken(value) && !value.contains("(") &&
                !value.contains("{") && !value.contains("[");
    }

    private boolean _isOptionalToken(String tokenValue) {
        return tokenValue.charAt(0) == '[';
    }

    private boolean _isRepeatToken(String tokenValue) {
        return tokenValue.charAt(0) == '{';
    }

    private boolean _isSequentialToken(String value) {
        return !value.contains("(") && !value.contains("{") &&
                !value.contains("[") && !value.contains("|");
    }

    private void _readFile(String filePath) throws IOException {
        File finalTokensFile = new File(filePath);

        FileInputStream fileInputStream = new FileInputStream(finalTokensFile);

        byte[] dataBytes = new byte[(int) finalTokensFile.length()];

        fileInputStream.read(dataBytes);
        fileInputStream.close();

        String strData = new String(dataBytes, "UTF-8");

        _scanner = new Scanner(strData);
    }

    private void _registerTokenTokenList(
            String splitter, BNFTokenImpl token, String tokenValue)
            throws Exception {

        for(String tokenName : tokenValue.trim().split(splitter)) {
            BNFToken bnfToken = _finalTokens.get(tokenName);

            if (bnfToken != null) {
                token.addToken(bnfToken);
                continue;
            }

            BNFTokenImpl childToken = _tokens.get(tokenName);

            if(childToken == null) {
                throw new Exception("Token " + tokenName + " does not " +
                        "exits");
            }

            token.addToken(childToken);
        }
    }

}
