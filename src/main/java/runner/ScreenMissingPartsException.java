package runner;

/**
 * An exception if a screen does not have all of its parts.
 * This will be thrown if a screen is missing a Model, View, or Controller
 */
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
