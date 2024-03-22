package com.coherentsolutions.section2;

public class ThreadWithJoin extends Thread {
    private String threadName;

    public ThreadWithJoin(String threadName) {
        this.threadName = threadName;
    }

    public void run() {
        System.out.println(threadName + " starting.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(threadName + " interrupted.");
        }
        System.out.println(threadName + " terminating.");
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadWithJoin firstThread = new ThreadWithJoin("FirstThread");
        ThreadWithJoin secondThread = new ThreadWithJoin("SecondThread");

        firstThread.start();
        System.out.println("Main thread waiting for FirstThread to complete.");
        firstThread.join();
        System.out.println("FirstThread completed, starting SecondThread.");
        secondThread.start();
        secondThread.join();
        System.out.println("SecondThread completed, main thread resumes.");
    }
}
