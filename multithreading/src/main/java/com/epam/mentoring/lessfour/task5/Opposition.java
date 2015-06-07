package com.epam.mentoring.lessfour.task5;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Aliaksei Vihuro <aliaksei_vihuro@epam.com>
 */
@Slf4j
public class Opposition {
    
    private final Long TIMEOUT = 30000L;
    private Long startTime;
    private Thread t1, t2;
    
    public class Counter {
        
        private volatile int count = 10;
        private final Object monitor = new Object();

        public void increment() {
            synchronized (monitor) {
                if (count == 20) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException ex) {
                        log.warn("We receive Interrupted Exception {}", Thread.currentThread().getName());
                    }
                }
                count++;
                monitor.notifyAll();
            }
        }

        public void decrement() {
            synchronized (monitor) {
                if (count == 0) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException ex) {
                        log.warn("We receive Interrupted Exception {}", Thread.currentThread().getName());
                    }
                }
                count--;
                monitor.notifyAll();
            }
        }

        public int get() {
            return count;
        }

    }

    public class Wrestler implements Runnable {

        private final Counter counter;
        private final boolean increment;
        private final Random rand;

        public Wrestler(Counter counter, boolean increment) {
            this.counter = counter;
            this.increment = increment;
            rand = new Random();
        }

        @Override
        public void run() {
            while (true) {
                if (increment) {
                    counter.increment();
                } else {
                    counter.decrement();
                }

                int x = counter.get();
                if (x < 0) {
                    throwException("We have below zero!");
                } else if (x > 20) {
                    throwException("We have greater than twenty");
                }

                log.info("Wrestler [{}] {}",Thread.currentThread().getName(), x);
                try {
                    Thread.sleep(rand.nextInt(100));
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        private void throwException(final String message) throws IllegalStateException {
            t1.interrupt();
            t2.interrupt();
            throw new IllegalStateException(message);
        }

    }

    public static void main(String[] args) {
        new Opposition().start();
    }

    private void start() {
        Counter counter = new Counter();
        t1 = new Thread(new Wrestler(counter, true));
        t2 = new Thread(new Wrestler(counter, false));
        t1.start();
        t2.start();
        startTime = System.currentTimeMillis();
        try {
            while (true) {
                Thread.sleep(100);
                boolean isTimeoutOver = ((System.currentTimeMillis() - startTime) >= TIMEOUT);
                if (isTimeoutOver) {
                    t1.interrupt();
                    t2.interrupt();
                }
                if (!(t1.isAlive() && t2.isAlive())) {
                    break;
                }
            }
        } catch (InterruptedException e) {
        }
        log.info("Finished");
    }

}
