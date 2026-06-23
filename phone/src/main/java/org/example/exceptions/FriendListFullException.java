package org.example.exceptions;

public class FriendListFullException extends RuntimeException {
    public FriendListFullException(String message) {
        super(message);
    }
}
