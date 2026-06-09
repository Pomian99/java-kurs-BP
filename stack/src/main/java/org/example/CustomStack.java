package org.example;

public class CustomStack<T> {
    private StackNode<T> head = null;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void push(T value) {
        head = new StackNode<>(value, head);
        size++;
    }

    public T pop() {
        if (size <= 0) {
            System.err.println("Trying to pop element from empty stack");
            return null;
        }
        T returnValue = head.getValue();
        head = head.getNext();
        size--;
        return returnValue;
    }

    public void remove(T value) {
        if (head == null) {
            System.err.println("Tried to remove element from empty stack");
            return;
        }
        if (head.getValue().equals(value)) {
            head = head.getNext();
            size--;
            return;
        }
        StackNode<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getValue().equals(value)) {
                current.setNext(current.getNext().getNext());
                size--;
                return;
            }
            current = current.getNext();
        }
        System.err.println("Tried to remove element not in stack");
    }
}