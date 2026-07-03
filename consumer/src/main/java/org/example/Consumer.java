package org.example;

public class Consumer implements Runnable {
    private final DataBuffer queue;
    private final int itemsToConsume;

    public Consumer(DataBuffer queue, int itemsToConsume) {
        this.queue = queue;
        this.itemsToConsume = itemsToConsume;
    }

    public void run() {
        System.out.printf("%s starting.%n", Thread.currentThread().getName());
        for (int i = 0; i < itemsToConsume; i++) {
            try {
                int item = queue.consume();
                System.out.printf("%s consumed item: %d%n", Thread.currentThread().getName(), item);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
