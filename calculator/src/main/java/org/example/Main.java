package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        boolean calculate = true;
        Scanner scannner = new Scanner(System.in);
        double result = 0.0;

        while (calculate) {
            double a = getDoubleFromUser("Enter first number:", scannner);

            System.out.println("Enter operator:");
            char operator = scannner.next().charAt(0);

            double b = getDoubleFromUser("Enter second number:", scannner);

            result = performCalculation(a, operator, b);
            if (!Double.isNaN(result)) {
                System.out.println("The result of your operation: " + result);
                System.out.println("Do you want to perform next calculation?");
                calculate = scannner.next().charAt(0) == 't';
            }
        }
        System.out.println(result % 2 == 0 ? "Last result is even" : "Last result is odd");
    }

    private static double getDoubleFromUser(String prompt, Scanner scanner){
        System.out.println(prompt);
        while (!scanner.hasNextDouble())
        {
            System.out.println("Given input is not double, enter new one");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private static double performCalculation(double num1, char operator, double num2) {
        return switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num2 == 0.0 ? Double.NaN : num1 / num2;
            case '%' -> num1 % num2;
            case '^' -> Math.pow(num1, num2);
            default -> Double.NaN;
        };
    }
}