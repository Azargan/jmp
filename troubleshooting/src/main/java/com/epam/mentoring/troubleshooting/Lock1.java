package com.epam.mentoring.troubleshooting;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

public class Lock1 {

    private Object resource1 = new Object();
    private Object resource2 = new Object();

    public static void main(String[] args) {
        new Lock1().run();
    }

    private void run() {
        new Thread(() -> {
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
        }).start();

        new Thread(() -> {
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
        }).start();

        ThreadDumper.collectDump();
    }
}