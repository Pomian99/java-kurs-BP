package org.example;

public class Main {
    public static void main(String[] args) {
        CustomStack<Integer> integerStack = new CustomStack<>();
        integerStack.push(1);
        integerStack.push(2);
        System.out.println(integerStack.pop());
        integerStack.push(3);
        System.out.println(integerStack.pop());
        System.out.println(integerStack.pop());

        CustomStack<Pair> pairStack = new CustomStack<>();
        pairStack.push(new Pair(1, 1));
        pairStack.push(new Pair(4, 5));
        pairStack.remove(new Pair(4, 5));
        System.out.println(pairStack.pop());
    }
}