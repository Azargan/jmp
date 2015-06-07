package com.epam.mentoring.lessfour.task6.consumer;

import com.epam.mentoring.lessfour.task6.Controller.Controller;
import static com.epam.mentoring.lessfour.task6.util.Sleeper.sleep;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Aliaksei Vihuro <aliaksei_vihuro@epam.com>
 */
@Slf4j
public class SimpleConsumer implements Consumer {

    private final Controller controller;
    private final String topic;

    public SimpleConsumer(final Controller controller, final String topic) {
        this.controller = controller;
        this.topic = topic;
    }

    @Override
    public void consume() {

        String message = controller.getMessage(topic);
        if (message != null) {
            log.info("Topic: {}; Message: {}", topic, message);
        }
    }

    @Override
    public void run() {
        while (true) {
            consume();
            sleep(500L);
        }
    }
}
