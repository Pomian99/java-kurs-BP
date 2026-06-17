package org.example.model;

import org.example.exception.ItemIsBorrowedException;
import org.example.exception.ItemNotBorrowedException;

public abstract class LibraryItem {
    private final String title;
    private boolean borrowed;

    public LibraryItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void borrow() throws ItemIsBorrowedException {
        if (borrowed) {
            throw new ItemIsBorrowedException(String.format("Item %s is already borrowed", title));
        }
        borrowed = true;
    }

    public void returnItem() throws ItemNotBorrowedException {
        if (!borrowed) {
            throw new ItemNotBorrowedException(String.format("Item %s is not borrowed", title));
        }
        borrowed = false;
    }
}
