package org.example;

public class Book extends LibraryItem {
    private final String author;
    private final int pages;
    private static int count;

    public Book(String title, String author, int pages) {
        super(title);
        this.author = author;
        this.pages = pages;
        count++;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return String.format("Book: %s by %s. Pages: %d", getTitle(), author, pages);
    }
}
