package org.example.model;

public record Person(String name, String surname, String phoneNumber) {
    @Override
    public String toString() {
        return String.format("%s %s %s", name, surname, phoneNumber);
    }
}
