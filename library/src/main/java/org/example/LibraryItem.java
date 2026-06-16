package org.example;

public abstract class LibraryItem {
    protected final String title;
    protected boolean isBorrowed = false;

    public LibraryItem(String title) {
        this.title = title;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow() throws ItemIsBorrowedException {
        if (isBorrowed) {
            throw new ItemIsBorrowedException(String.format("Item %s is already borrowed", title));
        }
        isBorrowed = true;
    }

    public void returnItem() throws ItemNotBorrowedException {
        if (!isBorrowed) {
            throw new ItemNotBorrowedException(String.format("Item %s is not borrowed", title));
        }
        isBorrowed = false;
    }
}
