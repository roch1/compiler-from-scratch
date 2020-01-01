package compilerfromscratch.token;

import compilerfromscratch.error.Error;
import compilerfromscratch.error.ErrorReporter;
import compilerfromscratch.error.StandardError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static compilerfromscratch.token.TokenType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

class ScannerTest {

    private ErrorReporter errorReporter;
    private List<Error> errors;

    @BeforeEach
    void setUp() {
        errors = new ArrayList<>();
        errorReporter = new StandardError(anyString(), errors);
    }

    @Test
    void givenValidSourceCode_ThenTokenizedWithoutErrors() {
        String sourceCode = "def function_name()\n" +
                            "    1\n" +
                            "end";

        List<Token> expected = Arrays.asList(new Token(DEF, "def", 1), new Token(IDENTIFIER, "function_name", 1),
                new Token(LEFT_PAREN, "(", 1), new Token(RIGHT_PAREN, ")", 1),
                new Token(INTEGER, "1", 2), new Token(END, "end", 3));
        List<Token> actual = new Scanner(sourceCode, errorReporter).tokenize();

        assertEquals(expected, actual);
        assertEquals(0, errors.size());
        assertFalse(errorReporter.hasErrors());
    }

    @Test
    void givenInvalidSourceCode_ThenTokenizedWithErrors() {
        String sourceCode = "def sum(¬)\n" +
                            "end";

        List<Error> expectedErrors = Collections.singletonList(new Error('¬', 1, "illegal character"));
        new Scanner(sourceCode, errorReporter).tokenize();

        assertEquals(expectedErrors, errors);
        assertEquals(1, errors.size());
        assertTrue(errorReporter.hasErrors());
    }

}