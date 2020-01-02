package compilerfromscratch.token;

// valid grammar of a language
public enum TokenType {
    // keyword tokens
    DEF,
    END,

    // literals
    IDENTIFIER,
    INTEGER,

    // single-character tokens
    LEFT_PAREN,
    RIGHT_PAREN,

    // end-of-file
    EOF
}
