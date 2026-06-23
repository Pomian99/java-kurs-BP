package org.example.exceptions;

public class CallHistoryFullException extends RuntimeException {
    public CallHistoryFullException(String message) {
        super(message);
    }
}
