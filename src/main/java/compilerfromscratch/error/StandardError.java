package compilerfromscratch.error;

import java.util.List;

public class StandardError implements ErrorReporter {

    private final String filePath;
    private final List<Error> errors;

    public StandardError(String filePath, List<Error> errors) {
        this.filePath = filePath;
        this.errors = errors;
    }

    @Override
    public void addError(Error error) {
        errors.add(error);
    }

    @Override
    public boolean hasError() {
        return errors.size() > 0;
    }

    @Override
    public void reportError() {
        for (Error error : errors) {
            System.err.println(filePath + ":" + error.getLineNumber() + ": " + error.getMessage());
        }

        System.out.println(errors.size() + " error(s)");
    }

}
