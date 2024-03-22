package com.coherentsolutions.section4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static final Lock lock = new ReentrantLock();
    private static int sharedResource = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                sharedResource++;
                System.out.println("Thread 1 incrementing shared resource");
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                sharedResource++;
                System.out.println("Thread 2 incrementing shared resource");
            } finally {
                lock.unlock();
            }
        });

        t1.start();
        t2.start();
    }
}
