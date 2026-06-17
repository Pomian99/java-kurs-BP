package org.example.ui;

public enum MenuOption {
    DISPLAY_AVAILABLE(1, "Display available items."),
    DISPLAY_BORROWED(2, "Display borrowed items."),
    BORROW_ITEM(3, "Borrow item by title."),
    RETURN_ITEM(4, "Return item by title."),
    DISPLAY_BOOKS_NUMBER(5, "Display number of books."),
    DISPLAY_MOVIES_NUMBER(6, "Display number of movies."),
    EXIT(7, "Exit program.");

    private final int number;
    private final String description;

    MenuOption(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public static MenuOption fromNumber(int number) {
        for (MenuOption option : MenuOption.values()) {
            if (option.number == number) {
                return option;
            }
        }
        return null;
    }
}
