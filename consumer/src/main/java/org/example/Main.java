package org.example;

import java.util.List;
import java.util.stream.IntStream;

public class Main {
    private static final BufferType BUFFER_TYPE = BufferType.SYNCHRONIZED;
    private static final long PRODUCER_HEAD_START_MS = 2000L;
    private static final int PRODUCER_COUNT = 2;
    private static final int CONSUMER_COUNT = 3;
    private static final int ITEMS_TO_PRODUCE = 30;
    private static final int BUFFER_CAPACITY = 5;

    public static void main(String[] args) {
        validateConfiguration();
        run();
    }

    private static void run() {
        DataBuffer buffer = createBuffer();
        List<Thread> producers = createProducers(buffer);
        List<Thread> consumers = createConsumers(buffer);

        producers.forEach(Thread::start);
        sleep(PRODUCER_HEAD_START_MS);
        consumers.forEach(Thread::start);

        waitFor(producers);
        waitFor(consumers);
    }

    private static DataBuffer createBuffer() {
        return switch (BUFFER_TYPE) {
            case SYNCHRONIZED -> new SynchronizedDataBuffer(BUFFER_CAPACITY);
            case LOCK -> new LockDataBuffer(BUFFER_CAPACITY);
        };
    }

    private static List<Thread> createProducers(DataBuffer buffer) {
        int itemsPerProducer = ITEMS_TO_PRODUCE / PRODUCER_COUNT;
        return IntStream.range(0, PRODUCER_COUNT)
                .mapToObj(i -> new Thread(
                        new Producer(buffer, i * itemsPerProducer, itemsPerProducer),
                        "Producer-" + i
                ))
                .toList();
    }

    private static List<Thread> createConsumers(DataBuffer buffer) {
        int itemsPerConsumer = ITEMS_TO_PRODUCE / CONSUMER_COUNT;
        return IntStream.range(0, CONSUMER_COUNT)
                .mapToObj(i -> new Thread(
                        new Consumer(buffer, itemsPerConsumer),
                        "Consumer-" + i
                ))
                .toList();
    }

    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private static void waitFor(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }

    private static void validateConfiguration() {
        if (PRODUCER_HEAD_START_MS < 0) {
            throw new IllegalArgumentException("Producer head start cannot be negative");
        }
        if (PRODUCER_COUNT <= 0) {
            throw new IllegalArgumentException("Producer count must be positive");
        }
        if (CONSUMER_COUNT <= 0) {
            throw new IllegalArgumentException("Consumer count must be positive");
        }
        if (ITEMS_TO_PRODUCE < 0) {
            throw new IllegalArgumentException("Items to produce cannot be negative");
        }
        if (ITEMS_TO_PRODUCE % PRODUCER_COUNT != 0) {
            throw new IllegalArgumentException("Items to produce must be divisible by producer count");
        }
        if (ITEMS_TO_PRODUCE % CONSUMER_COUNT != 0) {
            throw new IllegalArgumentException("Items to produce must be divisible by consumer count");
        }
        if (BUFFER_CAPACITY <= 0) {
            throw new IllegalArgumentException("Buffer capacity must be positive");
        }
    }

    private enum BufferType {
        SYNCHRONIZED,
        LOCK
    }
}
