package org.example;

public class Car extends Vehicle {
    private final int doors;

    public Car(String brand, String model, int year, FuelType fuelType, double maxFuel, int doors) {
        super(brand, model, year, fuelType, maxFuel, 16.8);
        this.doors = doors;
    }

    @Override
    public void displayInfo() {
        System.out.printf("%s %s year %d, fuel type %s\n", brand, model, year, fuelType.getName());
        System.out.println("doors: " + doors);
        System.out.printf("%.2f/%.2f fuel remaining\n", currentFuelAmount, maxFuel);
    }
}
