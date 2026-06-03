package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        rectangle();
    }

    public static void rectangle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input rectangle height");
        double h = scanner.nextDouble();

        System.out.println("Input rectangle width");
        double w = scanner.nextDouble();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 || i == h - 1 || j == 0 || j == w-1)
                    System.out.print('*');
                else
                    System.out.print(' ');
            }
            System.out.println();
        }
    }
}