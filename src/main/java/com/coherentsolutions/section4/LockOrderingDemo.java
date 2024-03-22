package com.coherentsolutions.section4;

public class LockOrderingDemo {
    private static final Object Resource1 = new Object();
    private static final Object Resource2 = new Object();

    private static void orderedLock() {
        Thread t1 = new Thread(() -> {
            synchronized (Resource1) {
                System.out.println("Thread 1: Locked Resource 1");
                synchronized (Resource2) {
                    System.out.println("Thread 1: Locked Resource 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (Resource1) {
                System.out.println("Thread 2: Locked Resource 1");
                synchronized (Resource2) {
                    System.out.println("Thread 2: Locked Resource 2");
                }
            }
        });

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        orderedLock();
    }
}
