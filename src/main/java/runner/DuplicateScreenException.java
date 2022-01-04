package runner;

public class DuplicateScreenException extends RuntimeException {

    public DuplicateScreenException() {
        super();
    }

    public DuplicateScreenException(String message) {
        super(message);
    }

    public DuplicateScreenException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateScreenException(Throwable cause) {
        super(cause);
    }

    protected DuplicateScreenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
