package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        rectangle();
        pyramid();
        matrix();
        password();
    }

    public static void rectangle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input rectangle height");
        double h = scanner.nextInt();

        System.out.println("Input rectangle width");
        double w = scanner.nextInt();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 || i == h - 1 || j == 0 || j == w - 1) {
                    System.out.print('*');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    public static void pyramid() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input pyramid height:");
        double h = scanner.nextInt();

        for (int i = 0; i < h; i++) {
            // Drawing spaces for row
            for (int j = 0; j < h - i; j++) {
                System.out.print(' ');
            }
            // Drawing stars for row
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }

    public static void matrix() {
        int[][] table = new int[3][3];
        int value = 1;

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = value++;
            }
        }

        for (int[] row : table) {
            for (int number : row) {
                System.out.print(number + " ");
            }
            System.out.println();
        }
    }

    public static void password() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input password:");
        String password = scanner.nextLine();

        for (int i = 0; i < password.length(); i++) {
            char checked = password.charAt(i);
            for (int j = i + 1; j < password.length(); j++) {
                if (checked == password.charAt(j)) {
                    System.out.println("Password doesn't contain unique characters.");
                    return;
                }
            }
        }
        System.out.println("Password contain only unique characters.");
    }
}