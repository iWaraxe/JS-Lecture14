package com.coherentsolutions.section3;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIncrement {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public AtomicInteger getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIncrement sharedCounter = new AtomicIncrement();

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                sharedCounter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                sharedCounter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count: " + sharedCounter.getCount());
    }
}
