package com.epam.mentoring.troubleshooting;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

public class ThreadDumper {
    public static void collectDump() {
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ThreadInfo[] threads = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);
            for(final ThreadInfo info : threads) {
                System.out.print(info);
            }
        }).start();
    }
}
