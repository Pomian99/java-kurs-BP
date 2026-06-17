package org.example.ui;

import org.example.model.Book;
import org.example.model.LibraryItem;
import org.example.model.Movie;
import org.example.exception.ItemIsBorrowedException;
import org.example.exception.ItemNotBorrowedException;
import org.example.exception.ItemNotFoundException;
import org.example.service.Library;

import java.util.List;
import java.util.Scanner;

public class TerminalInterface {
    private final Scanner scanner;
    private final Library library;

    public TerminalInterface() {
        scanner = new Scanner(System.in);
        library = new Library();
        library.addItem(new Book("Batman", "Danny O'Neil", 42));
        library.addItem(new Book("Witcher", "Andrzej Sapkowski", 168));
        library.addItem(new Book("Design Patterns", "Erich Gamma", 352));
        library.addItem(new Movie("Reservoir Dogs", "Quentin Tarantino", 95));
        library.addItem(new Movie("Baby Driver", "Edgar Wright", 119));
        library.addItem(new Movie("Snatch", "Guy Ritchie", 129));
        library.addItem(new Movie("Indiana Jones", "Steven Spielberg", 164));
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            printMenu();
            MenuOption option =MenuOption.fromNumber(parseIntegerInput());
            if (option == null) {
                System.out.println("Unknown Option.");
                continue;
            }
            switch (option) {
                case DISPLAY_AVAILABLE -> displayAvailable();
                case DISPLAY_BORROWED -> displayBorrowed();
                case BORROW_ITEM -> borrowByTitle();
                case RETURN_ITEM -> returnByTitle();
                case DISPLAY_BOOKS_NUMBER -> displayBooksNumber();
                case DISPLAY_MOVIES_NUMBER -> displayMoviesNumber();
                case EXIT -> exit = true;
            }
        }
    }

    private void printMenu() {
        System.out.println("""
                ============================
                Choose action:""");
        for (MenuOption option : MenuOption.values()) {
            System.out.printf("%d - %s%n", option.getNumber(), option.getDescription());
        }
    }

    private int parseIntegerInput() {
        String input = scanner.nextLine().strip();

        while (!input.matches("\\d+")) {
            System.out.println("Integer value expected. Try again.");
            input = scanner.nextLine().strip();
        }

        return Integer.parseInt(input);
    }

    private void displayAvailable() {
        System.out.println("Available items:");
        printItemList(library.getAvailable());
    }

    private void displayBorrowed() {
        System.out.println("Borrowed items:");
        printItemList(library.getBorrowed());
    }

    private void printItemList(List<LibraryItem> itemList) {
        for (LibraryItem item : itemList) {
            System.out.println(item);
        }
    }

    private void borrowByTitle() {
        System.out.println("Input title for item to borrow:");
        try {
            library.borrow(scanner.nextLine().strip());
            System.out.println("Successfully borrowed");
        } catch (ItemNotFoundException | ItemIsBorrowedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void returnByTitle() {
        System.out.println("Input title for item to return:");
        try {
            library.returnItem(scanner.nextLine().strip());
            System.out.println("Successfully returned");
        } catch (ItemNotFoundException | ItemNotBorrowedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayBooksNumber() {
        System.out.printf("Number of books in library: %d%n", library.getBookNumber());
    }

    private void displayMoviesNumber() {
        System.out.printf("Number of movies in library: %d%n", library.getMovieNumber());
    }
}
