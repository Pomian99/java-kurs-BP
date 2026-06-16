package org.example;

import java.math.BigDecimal;

public enum FuelType {
    DIESEL("diesel", "3.8"),
    PETROL("petrol", "8.0"),
    ELECTRIC("electric", "2.0");

    private final String name;
    private final BigDecimal fuelPrice;

    FuelType(String name, String fuelPrice) {
        this.name = name;
        this.fuelPrice = new BigDecimal(fuelPrice);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getFuelPrice() {
        return fuelPrice;
    }

    public BigDecimal calculatePrice(double amount) {
        return fuelPrice.multiply(BigDecimal.valueOf(amount));
    }
}
