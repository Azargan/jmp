package com.epam.mentoring.troubleshooting;

public class Lock4 {

    private Object resource = new Object();

    public static void main(String[] args) {
        new Lock4().run();
    }

    private void run() {

        createThreadsForResource(10, resource);

        synchronized (resource) {
            resource.notifyAll();
        }

        ThreadDumper.collectDump();
    }

    private void createThreadsForResource(int number, Object resource) {
        for (int i = 0; i < number; i++) {
            new Thread(() -> { tryToWorkWithResource(resource); }).start();
        }
    }

    private void tryToWorkWithResource(Object resource) {
        while (true) {
            synchronized (resource) {
                try {
                    resource.wait();
                    Thread.sleep(2000);
                    resource.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " has finished work with resource!");
            }
        }
    }

}