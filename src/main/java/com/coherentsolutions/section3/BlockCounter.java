package com.coherentsolutions.section3;

class BlockCounter {
    private int count = 0;
    private final Object lock = new Object();

    public void increment() {
        synchronized(lock) {
            count++;
        }
    }

    public int getCount() {
        synchronized(lock) {
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockCounter sharedBlockCounter = new BlockCounter();
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                sharedBlockCounter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                sharedBlockCounter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count from block synchronization: " + sharedBlockCounter.getCount());
    }
}
