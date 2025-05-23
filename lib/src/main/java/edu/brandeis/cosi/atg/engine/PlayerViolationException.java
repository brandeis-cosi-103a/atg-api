package edu.brandeis.cosi.atg.engine;

/**
 * Exception thrown when a Player violates the game rules or throws an
 * exception.
 */
public class PlayerViolationException extends Exception {

    /**
     * Constructs a PlayerViolationException with the specified detail message.
     *
     * @param message the detail message
     */
    public PlayerViolationException(String message) {
        super(message);
    }

    /**
     * Constructs a PlayerViolationException with the specified detail message and
     * cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public PlayerViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a PlayerViolationException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public PlayerViolationException(Throwable cause) {
        super(cause);
    }
}
