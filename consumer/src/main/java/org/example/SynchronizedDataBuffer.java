package org.example;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;

public class SynchronizedDataBuffer implements DataBuffer {
    protected final Queue<Integer> queue;
    protected final int capacity;

    public SynchronizedDataBuffer(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<>();
    }

    @Override
    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() >= capacity) {
            logMessage("SynchronizedDataBuffer full.");
            wait();
        }
        queue.add(item);
        notifyAll();
    }

    @Override
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            logMessage("SynchronizedDataBuffer empty.");
            wait();
        }
        int item = queue.poll();
        notifyAll();
        return item;
    }

    protected void logMessage(String message) {
        String threadName = Thread.currentThread().getName();
        String timestamp = LocalTime.now().toString();
        System.out.printf("[%s] [%s] - %s%n",
                timestamp, threadName, message);
    }
}