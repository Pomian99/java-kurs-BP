package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean calculate = true;
        Scanner scanner = new Scanner(System.in);
        double result = 0.0;

        while (calculate) {
            System.out.println("Enter expression:");

            String line = scanner.nextLine();
            String[] parts = line.split(" ");

            if (parts.length != 3) {
                System.out.println("Wrong format. Use: number operator number");
                continue;
            }
            double a = getDoubleFromText(parts[0]);
            char operator = parts[1].charAt(0);
            double b = getDoubleFromText(parts[2]);

            if (Double.isNaN(a) || Double.isNaN(b) || parts[1].length() != 1) {
                System.out.println("Wrong expression. Use: number operator number");
                continue;
            }
            result = performCalculation(a, operator, b);
            if (!Double.isNaN(result)) {
                System.out.println("The result of your operation: " + result);
                System.out.println("Do you want to perform next calculation?");
                calculate = scanner.nextLine().charAt(0) == 't';
            }
        }
        System.out.println(result % 2 == 0 ? "Last result is even" : "Last result is odd");
    }

    private static double getDoubleFromText(String text) {
        Scanner scanner = new Scanner(text);

        if (scanner.hasNextDouble()) {
            return scanner.nextDouble();
        }

        return Double.NaN;
    }

    private static double performCalculation(double num1, char operator, double num2) {
        return switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> {
                if (num2 == 0.0) {
                    System.out.println("Can't divide by zero. Provide correct arguments.");
                    yield Double.NaN;
                }
                yield num1 / num2;
            }
            case '%' -> num1 % num2;
            case '^' -> Math.pow(num1, num2);
            default -> {
                System.out.println("Unknown operator given. Try again and provide correct one.");
                yield Double.NaN;
            }
        };
    }
}