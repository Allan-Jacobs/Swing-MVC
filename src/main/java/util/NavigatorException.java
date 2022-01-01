package util;

/**
 * A unchecked exception if the target screen can not be navigated to
 */
public class NavigatorException extends RuntimeException {
    public NavigatorException(String errorMessage, Throwable err)
    {
        super(errorMessage, err);
    }

    public NavigatorException(String errorMessage)
    {
        super(errorMessage);
    }
}
