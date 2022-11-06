package com.entropy.syntax;

import com.entropy.common.FileWalker;

public class SyntaxAnalyzer {
    public static void validate() throws Exception {
        FileWalker.getNewInstance(System.getProperty("user.dir") +
                "/app/src/main/resources/test.cpp");

        SyntaxBuilder syntaxBuilder =
                new SyntaxBuilder();

        BNFTokenOptionalGroup bnfToken = syntaxBuilder.readBNF();

        bnfToken.validate();
    }
}
