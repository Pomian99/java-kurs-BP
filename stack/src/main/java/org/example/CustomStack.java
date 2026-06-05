package org.example;

public class CustomStack {
    private StackNode head = null;
    private int size = 0;

    CustomStack() {
    }

    public int getSize() {
        return size;
    }

    public void push(int value) {
        head = new StackNode(value, head);
        size++;
    }

    public Integer pop() {
        if (size <= 0) {
            System.err.println("Trying to pop element from empty stack");
            return null;
        }
        int returnValue = head.value();
        head = head.previous();
        size--;
        return returnValue;
    }
}
