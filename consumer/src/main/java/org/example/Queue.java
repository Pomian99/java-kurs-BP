package org.example;

import java.time.LocalTime;
import java.util.LinkedList;

public class Queue {
    protected final LinkedList<Integer> queue;
    protected final int capacity;
    protected boolean logInfo;

    public Queue(int capacity, boolean logInfo) {
        this.capacity = capacity;
        this.logInfo = logInfo;
        queue = new LinkedList<>();
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() >= capacity) {
            logMessage("Queue full.");
            wait();
        }
        queue.add(item);
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            logMessage("Queue empty.");
            wait();
        }
        int item = queue.removeFirst();
        notifyAll();
        return item;
    }

    protected void logMessage(String message) {
        if (logInfo) {
            String threadName = Thread.currentThread().getName();
            String timestamp = LocalTime.now().toString();
            System.out.printf("[%s] [%s] - %s%n",
                    timestamp, threadName, message);
        }
    }
}