package org.example;

public class Main {
    public static void main(String[] args) {
        CustomStack stack = new CustomStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.printf("value popped: %d, stack size %d%n", stack.pop(), stack.getSize());
        stack.push(4);
//        System.out.println(stack);
        stack.remove(1);
//        System.out.println(stack);
        System.out.printf("value popped: %d, stack size %d%n", stack.pop(), stack.getSize());
        System.out.printf("value popped: %d, stack size %d%n", stack.pop(), stack.getSize());
        System.out.printf("value popped: %d, stack size %d%n", stack.pop(), stack.getSize());
        stack.push(5);
        System.out.printf("value popped: %d, stack size %d%n", stack.pop(), stack.getSize());
        System.out.printf("value popped: %d, stack size %d%n", stack.pop(), stack.getSize());
    }
}