package org.example;

import lombok.*;

import java.util.Random;

@RequiredArgsConstructor
public class Producer implements Runnable {
    private final Queue queue;
    private final int itemsToProduceCount;

    public void run() {
        System.out.printf("%s starting.%n", Thread.currentThread().getName());
        Random random = new Random();
        for (int i = 0; i < itemsToProduceCount; i++) {
            try {
                queue.produce(i);
                Thread.sleep(random.nextInt(100, 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}