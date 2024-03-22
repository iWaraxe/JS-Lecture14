package com.coherentsolutions.section5;

import java.util.LinkedList;
import java.util.Queue;

class Buffer {
    private final Queue<Integer> queue;
    private final int maxSize;

    public Buffer(int size) {
        this.queue = new LinkedList<>();
        this.maxSize = size;
    }

    public synchronized void put(int value) throws InterruptedException {
        while (queue.size() == maxSize) {
            wait(); // Wait until the buffer has space
        }
        queue.add(value);
        notifyAll(); // Notify any waiting threads that the buffer has new data
    }

    public synchronized int get() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Wait until there is data in the buffer
        }
        int value = queue.remove();
        notifyAll(); // Notify any waiting threads that the buffer has space
        return value;
    }
}

class Producer implements Runnable {
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int value = 0;
        while (true) {
            try {
                Thread.sleep(1000); // Simulate time taken to produce an item
                buffer.put(value);
                System.out.println("Produced " + value);
                value++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

class Consumer implements Runnable {
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000); // Simulate time taken to consume an item
                int value = buffer.get();
                System.out.println("Consumed " + value);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10); // Buffer size of 10
        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}
