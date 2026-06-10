package org.example;

import java.math.BigDecimal;

public enum Ticket {
    CHILD("reduced", "0.5"),
    ADULT("regular", "0.0"),
    SENIOR("senior", "0.8");

    private final String name;
    private final BigDecimal discount;

    Ticket(String name, String discount) {
        this.name = name;
        this.discount = new BigDecimal(discount);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal calculatePrice(BigDecimal price) {
        return BigDecimal.ONE.subtract(this.getDiscount()).multiply(price);
    }
}
