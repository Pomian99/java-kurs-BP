package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final ArrayList<Long> fibonacciMemory = new ArrayList<>(List.of((long) 0, (long) 1));

    public static void main(String[] args) {
        System.out.println(fibonacci(14));
        System.out.println(fibonacci(20));
    }

    private static long fibonacci(int n) {
        if (fibonacciMemory.size() >= n+1){
            return fibonacciMemory.get(n);
        }
        fibonacciMemory.add(fibonacci(n - 1) + fibonacci(n - 2));
        return fibonacciMemory.get(n);
    }
}