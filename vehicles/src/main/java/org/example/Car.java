package org.example;

public class Car extends Vehicle {
    private final int doors;

    public Car(String brand, String model, int year, FuelType fuelType, double maxFuel, int doors) {
        super(brand, model, year, fuelType, maxFuel);
        this.doors = doors;
    }

    @Override
    public void drive() {
        if (this.currentFuelAmount <= this.fuelType.getFuelUsage()) {
            System.out.println("Too low on fuel. Time to refuel.");
            return;
        }
        this.currentFuelAmount -= this.fuelType.getFuelUsage();
        System.out.println("Driving car and consuming fuel.");
    }

    @Override
    public void displayInfo() {
        System.out.printf("%s %s year %d, fuel type %s\n", this.brand, this.model, this.year, this.fuelType.getName());
        System.out.println("doors: " + this.doors);
        System.out.printf("%.2f/%.2f fuel remaining\n", this.currentFuelAmount, this.maxFuel);
    }
}
