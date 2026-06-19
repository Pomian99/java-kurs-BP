package org.example.service;

import org.example.model.Book;
import org.example.model.ItemState;
import org.example.model.LibraryItem;
import org.example.model.Movie;
import org.example.exception.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {
    private final List<LibraryItem> items;

    public Library() {
        items = new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public List<LibraryItem> getAvailable() {
        return getItemsByState(ItemState.AVAILABLE);
    }

    public List<LibraryItem> getBorrowed() {
        return getItemsByState(ItemState.BORROWED);
    }

    public void borrow(String title) throws ItemNotFoundException {
        findByTitle(title)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Item %s does not exist in library", title)))
                .borrow();
    }

    public void returnItem(String title) throws ItemNotFoundException {
        findByTitle(title)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Item %s does not exist in library", title)))
                .returnItem();
    }

    public int getBookNumber() {
        return Book.getCount();
    }

    public int getMovieNumber() {
        return Movie.getCount();
    }

    private List<LibraryItem> getItemsByState(ItemState state) {
        return items.stream()
                .filter(item -> item.getState() == state)
                .collect(Collectors.toList());
    }

    private Optional<LibraryItem> findByTitle(String title) {
        return items.stream()
                .filter(item -> item.getTitle().equals(title))
                .findAny();
    }
}
