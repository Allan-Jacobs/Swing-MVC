package util;

/**
 * An unchecked exception if the target screen can not be navigated to
 */
public class NavigatorException extends RuntimeException {
    public NavigatorException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public NavigatorException(Throwable cause) {
        super(cause);
    }

    protected NavigatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NavigatorException() {
        super();
    }

    public NavigatorException(String errorMessage) {
        super(errorMessage);
    }
}
