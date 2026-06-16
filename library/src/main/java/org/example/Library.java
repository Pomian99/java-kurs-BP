package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library {
    List<LibraryItem> items;

    public Library() {
        items = new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public List<LibraryItem> getAvailable() {
        List<LibraryItem> availableItems = new ArrayList<>();
        for (LibraryItem item : items) {
            if (!item.isBorrowed()) {
                availableItems.add(item);
            }
        }
        return availableItems;
    }

    public List<LibraryItem> getBorrowed() {
        List<LibraryItem> borrowedItems = new ArrayList<>();
        for (LibraryItem item : items) {
            if (item.isBorrowed()) {
                borrowedItems.add(item);
            }
        }
        return borrowedItems;
    }

    public void borrow(String title) throws ItemNotFoundException {
        for (LibraryItem item : items) {
            if (item.getTitle().equals(title)) {
                item.borrow();
                return;
            }
        }
        throw new ItemNotFoundException(String.format("Item %s does not exist in library", title));
    }

    public void returnItem(String title) throws ItemNotFoundException {
        for (LibraryItem item : items) {
            if (item.getTitle().equals(title)) {
                item.returnItem();
                return;
            }
        }
        throw new ItemNotFoundException(String.format("Item %s does not exist in library", title));
    }

    public int getBookNumber() {
        return Book.getCount();
    }

    public int getMovieNumber() {
        return Movie.getCount();
    }
}
