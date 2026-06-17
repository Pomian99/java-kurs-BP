package org.example.model;

import org.example.exception.ItemIsBorrowedException;
import org.example.exception.ItemNotBorrowedException;

public abstract class LibraryItem {
    private final String title;
    private ItemState state;

    public LibraryItem(String title) {
        this.title = title;
        state = ItemState.AVAILABLE;
    }

    public String getTitle() {
        return title;
    }

    public ItemState getState() {
        return state;
    }

    public void borrow() throws ItemIsBorrowedException {
        if (state == ItemState.BORROWED) {
            throw new ItemIsBorrowedException(String.format("Item %s is already borrowed", title));
        }
        state = ItemState.BORROWED;
    }

    public void returnItem() throws ItemNotBorrowedException {
        if (state == ItemState.AVAILABLE) {
            throw new ItemNotBorrowedException(String.format("Item %s is not borrowed", title));
        }
        state = ItemState.AVAILABLE;
    }
}
