package com.epam.mentoring.lessfour.task6.message;

import lombok.Getter;

/**
 *
 * @author Aliaksei Vihuro <aliaksei_vihuro@epam.com>
 */
public class SimpleMessage implements Message {
    
    @Getter private final String topic;
    @Getter private final String message;

    public SimpleMessage(String topic, String message) {
        this.topic = topic;
        this.message = message;
    }
}
