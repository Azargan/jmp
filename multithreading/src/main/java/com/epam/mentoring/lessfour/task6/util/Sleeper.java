package com.epam.mentoring.lessfour.task6.util;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Aliaksei Vihuro <aliaksei_vihuro@epam.com>
 */
@Slf4j
public class Sleeper {
    public static void sleep(final Long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException ex) {
            log.warn("Thread {} throw InterruptedException", Thread.currentThread().getName());
        }
    }
}
