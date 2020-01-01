package compilerfromscratch.error;

public interface ErrorReporter {

    void addError(Error error);
    boolean hasErrors();
    void reportErrors();

}
