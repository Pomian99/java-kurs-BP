package org.example;

import java.util.Random;

public class Producer implements Runnable {
    private final DataBuffer queue;
    private final int firstItem;
    private final int itemsToProduceCount;

    public Producer(DataBuffer queue, int itemsToProduceCount) {
        this(queue, 0, itemsToProduceCount);
    }

    public Producer(DataBuffer queue, int firstItem, int itemsToProduceCount) {
        this.queue = queue;
        this.firstItem = firstItem;
        this.itemsToProduceCount = itemsToProduceCount;
    }

    public void run() {
        System.out.printf("%s starting.%n", Thread.currentThread().getName());
        Random random = new Random();
        for (int i = 0; i < itemsToProduceCount; i++) {
            try {
                queue.produce(firstItem + i);
                Thread.sleep(random.nextInt(100, 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
