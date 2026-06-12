package org.example;

public class Motorcycle extends Vehicle {
    public Motorcycle(String brand, String model, int year, FuelType fuelType, double maxFuel) {
        super(brand, model, year, fuelType, maxFuel);
    }

    @Override
    public void drive() {
        if (this.currentFuelAmount <= this.fuelType.getFuelUsage() * 0.2) {
            System.out.println("Too low on fuel. Time to refuel.");
            return;
        }
        this.currentFuelAmount -= this.fuelType.getFuelUsage() * 0.2;
        System.out.println("Driving motorcycle and consuming fuel.");
    }
}
