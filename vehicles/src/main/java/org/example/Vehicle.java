package org.example;

public abstract class Vehicle implements Drivable {
    protected final String brand;
    protected final String model;
    protected final int year;
    protected final FuelType fuelType;
    protected final double maxFuel;
    protected double currentFuelAmount;
    protected double fuelConsumption;

    public Vehicle(String brand, String model, int year, FuelType fuelType, double maxFuel, double fuelConsumption) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        if (maxFuel <= 0) {
            throw new IllegalArgumentException("Max Fuel must be positive value");
        }
        this.maxFuel = maxFuel;
        this.currentFuelAmount = maxFuel;
        this.fuelConsumption = fuelConsumption;
    }

    public void displayInfo() {
        System.out.printf("%s %s year %d, fuel type %s\n%.2f/%.2f fuel remaining\n",
                brand, model, year, fuelType.getName(), currentFuelAmount, maxFuel);
    }

    public void refuel(double amount) {
        if (currentFuelAmount + amount > maxFuel) {
            switch (fuelType) {
                case DIESEL, PETROL -> System.out.println("Requested Too much fuel. Give proper amount.");
                case ELECTRIC -> {
                    System.out.printf("Too much power supplied. Charged only %f/%f requested. Paid %s.\n",
                            maxFuel - currentFuelAmount, amount,
                            fuelType.calculatePrice(maxFuel - currentFuelAmount));
                    currentFuelAmount = maxFuel;
                }
            }
            return;
        }
        currentFuelAmount += amount;
        System.out.printf("Refueled %f. Paid %s.\n", amount, fuelType.calculatePrice(amount));
    }

    @Override
    public void drive(double distance) {
        if (currentFuelAmount <= fuelConsumption * distance / 100) {
            System.out.println("Too low on fuel. Time to refuel.");
            return;
        }
        currentFuelAmount -= fuelConsumption * distance / 100;
        System.out.println("Driving car and consuming fuel.");
    }
}
