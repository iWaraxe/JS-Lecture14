package com.coherentsolutions.section3;

public class SharedObject {
    volatile boolean flag = false;

    public void setFlag() {
        this.flag = true;
    }

    public boolean checkFlag() {
        return flag;
    }

    public static void main(String[] args) throws InterruptedException {
        SharedObject sharedObject = new SharedObject();
        Thread t = new Thread(() -> {
            while(!sharedObject.checkFlag()) {
                // Busy-wait loop
            }
            System.out.println("Detected flag state change to true!");
        });

        t.start();
        Thread.sleep(1000);
        sharedObject.setFlag();
        t.join();
    }
}
