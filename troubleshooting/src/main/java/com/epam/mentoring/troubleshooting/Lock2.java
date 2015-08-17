package com.epam.mentoring.troubleshooting;

public class Lock2 {

    private Object resource1 = new Object();
    private Object resource2 = new Object();

    public static void main(String[] args) {
        new Lock2().run();
    }

    private void run() {
        new Thread(() -> {lockResource1();}).start();
        new Thread(() -> {lockResource2();}).start();

        createThreadsForResource(3, resource1);
        createThreadsForResource(3, resource2);

        ThreadDumper.collectDump();
    }

    private void createThreadsForResource(int number, Object resource) {
        for (int i = 0; i < number; i++) {
            new Thread(() -> { tryToGetResource(resource); }).start();
        }
    }

    private void tryToGetResource(Object resource) {
        synchronized (resource) {
            try {
                Thread.sleep(2000);
                resource.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Lock doesn't work!");
        }
    }

    private void lockResource2() {
        synchronized (resource2) {
            try {
                synchronized (resource1) {
                    resource1.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Lock doesn't work!");
        }
    }

    private void lockResource1() {
        synchronized (resource1) {
            try {
                Thread.sleep(1000);
                synchronized (resource2) {
                    resource2.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Lock doesn't work!");
        }
    }
}