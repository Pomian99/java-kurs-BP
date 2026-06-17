package org.example.exception;

public class ItemIsBorrowedException extends RuntimeException {
    public ItemIsBorrowedException(String message) {
        super(message);
    }
}
