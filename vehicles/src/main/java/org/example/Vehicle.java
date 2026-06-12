package org.example;

public abstract class Vehicle implements Drivable {
    protected final String brand;
    protected final String model;
    protected final int year;
    protected final FuelType fuelType;
    protected final double maxFuel;
    protected double currentFuelAmount;

    public Vehicle(String brand, String model, int year, FuelType fuelType, double maxFuel) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.maxFuel = maxFuel;
        this.currentFuelAmount = maxFuel;
    }

    public void displayInfo() {
        System.out.printf("%s %s year %d, fuel type %s\n", this.brand, this.model, this.year, this.fuelType.getName());
        System.out.printf("%.2f/%.2f fuel remaining\n", this.currentFuelAmount, this.maxFuel);
    }

    public void refuel(double amount) {
        this.currentFuelAmount += amount;
        if (this.currentFuelAmount > this.maxFuel) {
            this.currentFuelAmount = this.maxFuel;
            switch (this.fuelType) {
                case DIESEL, PETROL ->
                        System.out.println("You overflowed gas tank. Gas station will have a talk with you.");
                case ELECTRIC -> System.out.println("Too much power supplied. Stopped charging.");
            }
        }
    }
}
