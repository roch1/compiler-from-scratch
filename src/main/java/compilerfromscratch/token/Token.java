package compilerfromscratch.token;

import java.util.Objects;

public final class Token {

    private final TokenType tokenType;
    private final String tokenValue;

    public Token(TokenType tokenType, String tokenValue) {
        this.tokenType = tokenType;
        this.tokenValue = tokenValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return tokenType == token.tokenType &&
                Objects.equals(tokenValue, token.tokenValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenType, tokenValue);
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenType=" + tokenType +
                ", tokenValue='" + tokenValue + '\'' +
                '}';
    }

}
