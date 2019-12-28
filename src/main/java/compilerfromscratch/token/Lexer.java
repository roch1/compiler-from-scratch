package compilerfromscratch.token;

// lexer api:
// 1) instantiate by passing in path to a source file as a string
// 2) call the tokenize method, which will return tokens
// 3) print out the tokens (for testing)

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class Lexer {

    private final String filePath;

    public Lexer(String filePath) {
        this.filePath = filePath;
    }

    public List<Token> tokenize() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        String source = new String(bytes, StandardCharsets.UTF_8);
        return new Scanner(source).scan();
    }

}
