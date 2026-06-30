package org.example;

import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        queueMain(
                new LockQueue(5, true),
                1000L
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

        System.out.println("Ended");
    }
}