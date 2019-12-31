package compilerfromscratch.token;

// lexer api:
// 1) instantiate by passing in path to a source file as a string
// 2) call the tokenize method, which will return tokens
// 3) print out the tokens (for testing)

import compilerfromscratch.error.Error;
import compilerfromscratch.error.ErrorReporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static compilerfromscratch.token.TokenType.*;

public final class Scanner {

    private static final Map<String, TokenType> KEYWORDS;
    static {
        KEYWORDS = new HashMap<>();
        KEYWORDS.put("def", DEF);
        KEYWORDS.put("end", END);
    }

    private final List<Token> tokens = new ArrayList<>();
    private final String source;
    private final ErrorReporter errorReporter;

    public Scanner(String source, ErrorReporter errorReporter) {
        this.source = source;
        this.errorReporter = errorReporter;
    }

    private int current = 0;
    private int startOfCurrent = 0;
    private int line = 1;

    // tokenize source code by going through it char by char
    public List<Token> tokenize() {
        while (!isAtEnd()) {
            startOfCurrent = current;
            scanToken();
        }
        return tokens;
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case '(':
                addToken(LEFT_PAREN);
                break;
            case ')':
                addToken(RIGHT_PAREN);
                break;
            case '\n':
                line++;
                break;
            case ' ':
            case '\r':
            case '\t':
                break;
            default:
                if (isAlpha(c)) {
                    identifier();
                } else if (isDigit(c)) {
                    number();
                } else {
                    errorReporter.addError(new Error(c, line, "illegal character: " + c));
                }
                break;
        }
    }

    // how we know we've tokenized all of the source code
    private boolean isAtEnd() {
        return current >= source.length();
    }

    // get the current char of source code, and increment pointer variable to point to next char
    private char advance() {
        char currentChar = source.charAt(current);
        current++;
        return currentChar;
    }

    // allows us to just see what the current char is, without incrementing to the next char
    private char peek() {
        if (isAtEnd()) {
            return '\0';
        }
        return source.charAt(current);
    }

    // this is why using chars is useful, because they have a numeric value we can test against
    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                c == '_';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
    ///////////////////////////////////////////////////////////////////////////

    private void identifier() {
        while (isAlphaNumeric(peek())) {
            advance();
        }
        String text = getTokenValue();
        TokenType tokenType = KEYWORDS.get(text); // see if this token value is a keyword
        if (tokenType == null) {
            tokenType = IDENTIFIER;
        }
        addToken(tokenType, text);
    }

    private void number() {
        while (isDigit(peek())) {
            advance();
        }
        addToken(INTEGER);
    }

    private String getTokenValue() {
        return source.substring(startOfCurrent, current);
    }

    private void addToken(TokenType tokenType) {
        String tokenValue = getTokenValue();
        addToken(tokenType, tokenValue);
    }

    private void addToken(TokenType tokenType, String tokenValue) {
        tokens.add(new Token(tokenType, tokenValue, line));
    }

}
