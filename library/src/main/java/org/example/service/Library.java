package org.example.service;

import org.example.model.Book;
import org.example.model.LibraryItem;
import org.example.model.Movie;
import org.example.exception.ItemNotFoundException;

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
        LibraryItem item = findByTitle(title);
        if (item == null) {
            throw new ItemNotFoundException(String.format("Item %s does not exist in library", title));
        }
        item.borrow();
    }

    public void returnItem(String title) throws ItemNotFoundException {
        LibraryItem item = findByTitle(title);
        if (item == null) {
            throw new ItemNotFoundException(String.format("Item %s does not exist in library", title));
        }
        item.returnItem();
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

    private LibraryItem findByTitle(String title) {
        for (LibraryItem item : items) {
            if (item.getTitle().equals(title)) {
                return item;
            }
        }
        return null;
    }
}
