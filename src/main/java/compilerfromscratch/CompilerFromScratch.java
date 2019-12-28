package compilerfromscratch;

import compilerfromscratch.token.Lexer;
import compilerfromscratch.token.Token;

import java.io.IOException;
import java.util.List;

public class CompilerFromScratch {

    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        List<Token> tokens = new Lexer(filePath).tokenize();
        System.out.println(tokens);
    }

}
