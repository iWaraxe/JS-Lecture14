package com.coherentsolutions.section2;

public class ThreadLifecycleDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Inside the run() method, the thread is in the RUNNABLE state.
                try {
                    // The thread moves to the TIMED_WAITING state because of the sleep method.
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Handle interrupted exception
                }
                // After run() completes, the thread will enter the TERMINATED state.
            }
        });

        // The thread is in NEW state right after creation.
        System.out.println("State after creation: " + thread.getState());
        thread.start();
        // After calling start(), the thread is in RUNNABLE state (or could be in TIMED_WAITING if it's already sleeping).
        System.out.println("State after calling start(): " + thread.getState());
        // Main thread is going to wait for the thread to finish.
        thread.join();
        // After the thread finishes run(), it is in the TERMINATED state.
        System.out.println("State after the thread completes its execution: " + thread.getState());
        thread.start();
    }
}
