package org.syntax;

/**
 * Custom exception that will be thrown in {@link Exceptions} class
 */
public class NotOldEnoughException extends Exception {

    public NotOldEnoughException() {
    }

    public NotOldEnoughException(String message) {
        super(message);
    }
}
