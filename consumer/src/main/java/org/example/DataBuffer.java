package org.example;

public interface DataBuffer {
    void produce(int item) throws InterruptedException;
    int consume() throws InterruptedException;
}
