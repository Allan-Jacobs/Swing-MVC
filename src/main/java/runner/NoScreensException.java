package runner;

/**
 * An exception thrown when no screens exist.
 */
public class NoScreensException extends Exception {
    public NoScreensException() {
        super();
    }

    public NoScreensException(String message) {
        super(message);
    }

    public NoScreensException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoScreensException(Throwable cause) {
        super(cause);
    }

    public NoScreensException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
