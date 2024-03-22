package com.coherentsolutions.section4;

public class DeadlockDemo {
    private static final Object Resource1 = new Object();
    private static final Object Resource2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (Resource1) {
                System.out.println("Thread 1: Locked Resource 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                synchronized (Resource2) {
                    System.out.println("Thread 1: Locked Resource 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (Resource2) {
                System.out.println("Thread 2: Locked Resource 2");
                synchronized (Resource1) {
                    System.out.println("Thread 2: Locked Resource 1");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
