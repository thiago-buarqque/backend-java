package com.entropy;

import com.entropy.common.FileWalker;
import com.entropy.lexical.LexicalAnalyzer;
import com.entropy.syntax.BNFTokenOptionalGroup;
import com.entropy.syntax.SyntaxBuilder;

public class App {
    public static void main(String[] args) throws Exception {
        FileWalker.getNewInstance(System.getProperty("user.dir") +
                "/app/src/main/resources/test.cpp");

        LexicalAnalyzer lexicalAnalyzer = LexicalAnalyzer.getInstance();

        lexicalAnalyzer.buildTokens();

        SyntaxBuilder syntaxBuilder = new SyntaxBuilder();
        BNFTokenOptionalGroup bnf = syntaxBuilder.readBNF();

        bnf.validate();

        // In case you create a token on BNF that starts with the same token
        // as another token, put it on an optional group token.
        //
        // Example:
        // <int_var> ::= <int> <identifier> ...
        // <int_array> ::= <int> <identifier> ...
        //
        // Put it like this:
        // <int_declaration> ::= <int_var> | <int_array>
    }
}
