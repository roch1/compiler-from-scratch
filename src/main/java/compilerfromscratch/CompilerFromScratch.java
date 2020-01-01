package compilerfromscratch;

import compilerfromscratch.error.ErrorReporter;
import compilerfromscratch.error.StandardError;
import compilerfromscratch.token.Scanner;
import compilerfromscratch.token.Token;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CompilerFromScratch {

    public static void main(String[] args) throws IOException {

        if (args.length == 1) {
            String filePath = args[0];
            ErrorReporter errorReporter = new StandardError(filePath, new ArrayList<>());
            List<Token> tokens = runSourceFile(filePath, errorReporter);
            if (errorReporter.hasErrors()) {
                errorReporter.reportErrors();
                System.exit(65);
            } else {
                System.out.println(tokens);
            }
        } else {
            System.out.println("no support for a REPL yet");
            System.exit(64);
        }
        
    }

    private static List<Token> runSourceFile(String filePath, ErrorReporter errorReporter) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        String source = new String(bytes, StandardCharsets.UTF_8);
        return new Scanner(source, errorReporter).tokenize();
    }

}
