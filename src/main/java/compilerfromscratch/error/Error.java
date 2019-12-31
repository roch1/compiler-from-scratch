package compilerfromscratch.error;

import java.util.Objects;

public final class Error {

    private final char c;
    private final int lineNumber;
    private final String message;

    public Error(char c, int lineNumber, String message) {
        this.c = c;
        this.lineNumber = lineNumber;
        this.message = message;
    }

    public char getC() {
        return c;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error error = (Error) o;
        return c == error.c &&
                lineNumber == error.lineNumber &&
                Objects.equals(message, error.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(c, lineNumber, message);
    }

    @Override
    public String toString() {
        return "Error{" +
                "c=" + c +
                ", lineNumber=" + lineNumber +
                ", message='" + message + '\'' +
                '}';
    }

}
