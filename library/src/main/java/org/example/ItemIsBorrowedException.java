package org.example;

public class ItemIsBorrowedException extends RuntimeException {
    public ItemIsBorrowedException(String message) {
        super(message);
    }
}
