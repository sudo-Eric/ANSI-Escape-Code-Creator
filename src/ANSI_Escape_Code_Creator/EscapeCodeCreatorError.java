package ANSI_Escape_Code_Creator;

/**
 * @author Eric Heinke (sudo-Eric)
 * @version 1.2.2
 */
@SuppressWarnings("unused")
public class EscapeCodeCreatorError extends RuntimeException {
    private static final long serialVersionUID = 4056768650584583722L;

    public EscapeCodeCreatorError() {
        super();
    }

    public EscapeCodeCreatorError(String message) {
        super(message);
    }

    public EscapeCodeCreatorError(String message, Throwable cause) {
        super(message, cause);
    }

    public EscapeCodeCreatorError(Throwable cause) {
        super(cause);
    }

    public EscapeCodeCreatorError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

/**
 * @author Eric Heinke (sudo-Eric)
 * @version 1.2.2
 */
@SuppressWarnings("unused")
class EscapeCodeColorError extends EscapeCodeCreatorError {
    private static final long serialVersionUID = -7268100203238610224L;

    public EscapeCodeColorError() {
        super();
    }

    public EscapeCodeColorError(String message) {
        super(message);
    }

    public EscapeCodeColorError(String message, Throwable cause) {
        super(message, cause);
    }

    public EscapeCodeColorError(Throwable cause) {
        super(cause);
    }

    public EscapeCodeColorError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

/**
 * @author Eric Heinke (sudo-Eric)
 * @version 1.2.2
 */
@SuppressWarnings("unused")
class EscapeCodeColorModeError extends EscapeCodeCreatorError {
    private static final long serialVersionUID = -7268100203238610224L;

    public EscapeCodeColorModeError() {
        super();
    }

    public EscapeCodeColorModeError(String message) {
        super(message);
    }

    public EscapeCodeColorModeError(String message, Throwable cause) {
        super(message, cause);
    }

    public EscapeCodeColorModeError(Throwable cause) {
        super(cause);
    }

    public EscapeCodeColorModeError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}