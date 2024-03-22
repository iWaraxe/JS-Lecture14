package com.coherentsolutions.section2;

class Message {
    private String content;
    private boolean empty = true;

    public synchronized String take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        empty = true;
        notifyAll();
        return content;
    }

    public synchronized void put(String value) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        empty = false;
        content = value;
        notifyAll();
    }
}

class Producer implements Runnable {
    private Message message;

    public Producer(Message message) {
        this.message = message;
    }

    public void run() {
        String[] messages = {"Message One", "Message Two", "Message Three", "Done"};
        for (String msg : messages) {
            message.put(msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}

class Consumer implements Runnable {
    private Message message;

    public Consumer(Message message) {
        this.message = message;
    }

    public void run() {
        for (String msg = message.take(); !msg.equals("Done"); msg = message.take()) {
            System.out.println("Consumed " + msg);
        }
    }
}

public class InterThreadCommunicationDemo {
    public static void main(String[] args) {
        Message message = new Message();
        Thread producerThread = new Thread(new Producer(message));
        Thread consumerThread = new Thread(new Consumer(message));
        producerThread.start();
        consumerThread.start();
    }
}
