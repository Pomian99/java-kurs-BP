package org.example;

public class Main {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = new Motorcycle("Suzuki", "Bike", 2001, FuelType.PETROL, 50.0);
        vehicles[1] = new Car("Renault", "Megane", 2000, FuelType.DIESEL, 100.0, 5);
        vehicles[2] = new Car("Ferrari", "EV", 2026, FuelType.ELECTRIC, 1000.0, 3);

        for (Vehicle vehicle : vehicles) {
            vehicle.displayInfo();
            vehicle.drive();
            vehicle.displayInfo();
        }

        for (int i = 0; i < 10; i++) {
            for (Vehicle vehicle : vehicles) {
                vehicle.drive();
            }
        }

        for (Vehicle vehicle : vehicles) {
            vehicle.refuel(60);
        }
    }
}