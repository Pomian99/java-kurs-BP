package org.example;

public enum FuelType {
    DIESEL("diesel", 10.0),
    PETROL("petrol", 8.0),
    ELECTRIC("electric", 20.0);

    private final String name;
    private final double fuelUsage;

    FuelType(String name, double fuelUsage) {
        this.name = name;
        this.fuelUsage = fuelUsage;
    }

    String getName() {
        return this.name;
    }

    double getFuelUsage() {
        return fuelUsage;
    }
}
