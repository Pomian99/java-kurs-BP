package org.example;

public class ItemNotBorrowedException extends RuntimeException {
    public ItemNotBorrowedException(String message) {
        super(message);
    }
}
