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
        int returnValue = head.getValue();
        head = head.getNext();
        size--;
        return returnValue;
    }

    public void remove(int value) {
        if (head == null) {
            System.err.println("Tried to remove element from empty stack");
            return;
        }
        if (head.getValue() == value) {
            head = head.getNext();
            size--;
            return;
        }
        StackNode current = head;
        while (current.getNext() != null) {
            if (current.getNext().getValue() == value) {
                current.setNext(current.getNext().getNext());
                size--;
                return;
            }
            current = current.getNext();
        }
        System.err.println("Tried to remove element not in stack");
    }
}
