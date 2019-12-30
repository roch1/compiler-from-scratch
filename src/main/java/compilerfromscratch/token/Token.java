package compilerfromscratch.token;

import java.util.Objects;

public final class Token {

    private final TokenType tokenType;
    private final String tokenValue;
    private final int lineNumber;

    public Token(TokenType tokenType, String tokenValue, int lineNumber) {
        this.tokenType = tokenType;
        this.tokenValue = tokenValue;
        this.lineNumber = lineNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return lineNumber == token.lineNumber &&
                tokenType == token.tokenType &&
                Objects.equals(tokenValue, token.tokenValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenType, tokenValue, lineNumber);
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenType=" + tokenType +
                ", tokenValue='" + tokenValue + '\'' +
                ", lineNumber=" + lineNumber +
                '}';
    }

}
