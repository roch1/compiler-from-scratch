package compilerfromscratch.error;

public interface ErrorReporter {

    void addError(Error error);
    boolean hasError();
    void reportError();

}
