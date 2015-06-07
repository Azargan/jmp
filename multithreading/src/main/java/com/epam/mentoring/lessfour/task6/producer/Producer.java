package com.epam.mentoring.lessfour.task6.producer;

import com.epam.mentoring.lessfour.task6.message.Message;

/**
 *
 * @author Aliaksei Vihuro <aliaksei_vihuro@epam.com>
 */
public interface Producer extends Runnable {
    public Message produce();
}
