package org.example;

import lombok.*;

@RequiredArgsConstructor
public class Consumer implements Runnable {
    private final Queue queue;
    private final int itemsToConsume;

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