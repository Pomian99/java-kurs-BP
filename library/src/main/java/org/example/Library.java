package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<LibraryItem> items;

    public Library() {
        items = new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public List<LibraryItem> getAvailable() {
        return getItemsByBorrowedStatus(false);
    }

    public List<LibraryItem> getBorrowed() {
        return getItemsByBorrowedStatus(true);
    }

    public void borrow(String title) throws ItemNotFoundException {
        findByTitle(title).borrow();
    }

    public void returnItem(String title) throws ItemNotFoundException {
        findByTitle(title).returnItem();
    }

    public int getBookNumber() {
        return Book.getCount();
    }

    public int getMovieNumber() {
        return Movie.getCount();
    }

    private List<LibraryItem> getItemsByBorrowedStatus(boolean borrowed) {
        List<LibraryItem> matchingItems = new ArrayList<>();
        for (LibraryItem item : items) {
            if (item.isBorrowed() == borrowed) {
                matchingItems.add(item);
            }
        }
        return matchingItems;
    }

    private LibraryItem findByTitle(String title) throws ItemNotFoundException {
        for (LibraryItem item : items) {
            if (item.getTitle().equals(title)) {
                return item;
            }
        }
        throw new ItemNotFoundException(String.format("Item %s does not exist in library", title));
    }

}
