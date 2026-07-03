package org.example;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockDataBuffer implements DataBuffer {
    protected final Queue<Integer> queue;
    protected final int capacity;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition queueEmptyCondition = lock.newCondition();
    private final Condition queueFullCondition = lock.newCondition();

    public LockDataBuffer(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<>();
    }

    @Override
    public void produce(int item) throws InterruptedException {
        try {
            lock.lock();
            while (queue.size() >= capacity) {
                logMessage("LockDataBuffer full.");
                queueFullCondition.await();
            }
            queue.add(item);
            queueEmptyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int consume() throws InterruptedException {
        try {
            lock.lock();
            while (queue.isEmpty()) {
                logMessage("LockDataBuffer empty.");
                queueEmptyCondition.await();
            }
            return queue.poll();
        } finally {
            queueFullCondition.signalAll();
            lock.unlock();
        }
    }

    protected void logMessage(String message) {
        String threadName = Thread.currentThread().getName();
        String timestamp = LocalTime.now().toString();
        System.out.printf("[%s] [%s] - %s%n",
                timestamp, threadName, message);
    }
}
