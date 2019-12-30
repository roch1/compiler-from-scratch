package compilerfromscratch;

import compilerfromscratch.token.Scanner;
import compilerfromscratch.token.Token;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CompilerFromScratch {

    public static void main(String[] args) throws IOException {

        if (args.length == 1) {
            List<Token> tokens = runSourceFile(args[0]);
            System.out.println(tokens);
        } else {
            System.out.println("no support for a REPL yet");
            System.exit(64);
        }
        
    }

    private static List<Token> runSourceFile(String filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        String source = new String(bytes, StandardCharsets.UTF_8);
        return new Scanner(source).tokenize();
    }

}
