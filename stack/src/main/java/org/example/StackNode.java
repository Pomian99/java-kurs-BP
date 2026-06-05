package org.example;

public class StackNode {
    private final int value;
    private StackNode next;

    public StackNode(int value, StackNode previous) {
        this.value = value;
        this.next = previous;
    }

    public int getValue() {
        return value;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }

    public StackNode getNext() {
        return next;
    }
}
