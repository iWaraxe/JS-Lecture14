package com.coherentsolutions.section2;

public class YieldDemo implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " yielding control...");
            Thread.yield();
            System.out.println(Thread.currentThread().getName() + " resumed execution.");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new YieldDemo(), "Thread-1");
        Thread t2 = new Thread(new YieldDemo(), "Thread-2");

        t1.start();
        t2.start();
    }
}
