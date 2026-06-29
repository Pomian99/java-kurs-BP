package org.example;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PongThread implements Runnable{
    private final String word;
    private final int duration;
    @Override
    public void run() {
        for (int i = 0; i < duration; i++) {
            System.out.println(word);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
