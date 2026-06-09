package org.example;

public class StackNode<T> {
    private final T value;
    private StackNode<T> next;

    public StackNode(T value, StackNode<T> previous) {
        this.value = value;
        this.next = previous;
    }

    public T getValue() {
        return value;
    }

    public void setNext(StackNode<T> next) {
        this.next = next;
    }

    public StackNode<T> getNext() {
        return next;
    }
}