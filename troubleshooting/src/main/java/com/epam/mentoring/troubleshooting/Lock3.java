package com.epam.mentoring.troubleshooting;

public class Lock3 {

    private Object resource1 = new Object();
    private Object resource2 = new Object();
    private Object resource3 = new Object();
    private Object resource4 = new Object();

    public static void main(String[] args) {
        new Lock3().run();
    }

    private void run() {
        new Thread(() -> {lockAndWait(resource1, resource4);}).start();
        new Thread(() -> {lockAndWait(resource2, resource1);}).start();
        new Thread(() -> {lockAndWait(resource3, resource2);}).start();
        new Thread(() -> {lockAndWait(resource4, resource3);}).start();

        ThreadDumper.collectDump();
    }

    private void lockAndWait(Object lock, Object wait) {
        synchronized (lock) {
            try {
                Thread.sleep(1000);
                synchronized (wait) {
                    wait.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Lock doesn't work!");
        }
    }
}