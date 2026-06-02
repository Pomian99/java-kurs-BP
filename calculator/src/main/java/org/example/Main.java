package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        boolean calculate = true;
        Scanner scan = new Scanner(System.in);
        double result = 0.0;

        while (calculate) {
            System.out.println("Enter first number:");
            double a = scan.nextDouble();

            System.out.println("Enter operator:");
            char oper = scan.next().charAt(0);

            System.out.println("Enter second number:");
            double b = scan.nextDouble();

            result = performCalculation(a, oper, b);
            if (Double.isNaN(result)) {
                System.out.println("Error during calculation");
            } else {
                System.out.println("The result of your operation: " + result);
                System.out.println("Do you want to perform next calculation?");
                calculate = scan.next().charAt(0) == 't';
            }
        }
        System.out.println(result % 2 == 0 ? "Last result is even" : "Last result is odd");
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