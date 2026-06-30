package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockQueue extends Queue {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition queueEmptyCondition = lock.newCondition();
    private final Condition queueFullCondition = lock.newCondition();

    public LockQueue(int capacity, boolean logInfo) {
        super(capacity, logInfo);
    }

    @Override
    public void produce(int item) throws InterruptedException {
        try {
            lock.lock();
            while (queue.size() >= capacity) {
                logMessage("Queue full.");
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
                logMessage("Queue empty.");
                queueEmptyCondition.await();
            }
            return queue.removeFirst();
        } finally {
            queueFullCondition.signalAll();
            lock.unlock();
        }
    }
}