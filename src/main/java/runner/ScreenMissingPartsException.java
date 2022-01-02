package runner;

public class ScreenMissingPartsException extends Exception {
    public ScreenMissingPartsException() {
        super();
    }

    public ScreenMissingPartsException(String message) {
        super(message);
    }

    public ScreenMissingPartsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScreenMissingPartsException(Throwable cause) {
        super(cause);
    }

    protected ScreenMissingPartsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
