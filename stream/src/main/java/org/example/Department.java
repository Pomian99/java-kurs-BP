package org.example;

public enum Department {
    IT("IT"),
    ACCOUNTING("Accounting"),
    MANAGEMENT("Management"),
    MARKETING("Marketing");

    private final String name;

    Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
