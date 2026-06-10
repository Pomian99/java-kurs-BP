package org.example;

public enum Ticket {
    CHILD("reduced", 0.5),
    ADULT("regular", 0.0),
    SENIOR("senior", 0.8);

    private final String name;
    private final double discount;

    Ticket(String name, double discount) {
        this.name = name;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public double getDiscount() {
        return discount;
    }

    public double calculatePrice(double price) {
        return (1 - this.getDiscount()) * price;
    }
}
