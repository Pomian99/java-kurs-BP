package org.example;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        queueMain(
                new LockQueue(5, true),
                2000L
        );
    }

    private static void queueMain(Queue queue, long producerHeadStart) {
        Thread producer = new Thread(
                new Producer(queue, 30),
                "Producer"
        );
        List<Thread> consumers = IntStream
                .range(1, 4)
                .mapToObj(i -> new Thread(
                        new Consumer(queue, 10),
                        "Consumer-" + i
                ))
                .toList();

        producer.start();
        try {
            Thread.sleep(producerHeadStart);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        consumers.forEach(Thread::start);

        try {
            producer.join();
            for (Thread consumer : consumers) {
                consumer.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void executorsMain(Queue queue, long producerHeadStart) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(new Producer(queue, 30));
        try {
            Thread.sleep(producerHeadStart);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        for (int i = 1; i <= 3; i++) {
            executor.submit(new Consumer(queue, 10));
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}