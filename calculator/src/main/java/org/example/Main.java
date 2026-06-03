package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean calculate = true;
        Scanner scanner = new Scanner(System.in);
        double result = 0.0;

        while (calculate) {
            double a = getDoubleFromUser("Enter first number:", scanner);

            System.out.println("Enter operator:");
            char operator = scanner.next().charAt(0);

            double b = getDoubleFromUser("Enter second number:", scanner);

            result = performCalculation(a, operator, b);
            if (!Double.isNaN(result)) {
                System.out.println("The result of your operation: " + result);
                System.out.println("Do you want to perform next calculation?");
                calculate = scanner.next().charAt(0) == 't';
            }
        }
        System.out.println(result % 2 == 0 ? "Last result is even" : "Last result is odd");
    }

    private static double getDoubleFromUser(String prompt, Scanner scanner) {
        System.out.println(prompt);
        while (!scanner.hasNextDouble()) {
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