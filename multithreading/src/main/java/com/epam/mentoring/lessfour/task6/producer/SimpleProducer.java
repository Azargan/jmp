package com.epam.mentoring.lessfour.task6.producer;

import com.epam.mentoring.lessfour.task6.Controller.Controller;
import com.epam.mentoring.lessfour.task6.message.Message;
import com.epam.mentoring.lessfour.task6.message.SimpleMessage;
import static com.epam.mentoring.lessfour.task6.util.Sleeper.sleep;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Aliaksei Vihuro <aliaksei_vihuro@epam.com>
 */
@Slf4j
public class SimpleProducer implements Producer {
    
    private Long counter = 0L;
    @Getter private final String topic;
    @Getter private final Controller controller;
    
    public SimpleProducer(final Controller controller, final String topic) {
        this.controller = controller;
        this.topic = topic;
    }

    @Override
    public Message produce() {
        final String message = topic + " message number " + counter++ + ".";
        return new SimpleMessage(topic, message);
    }

    @Override
    public void run() {
        while (true) {
            final Message message = produce();
            while (!getController().submit(message)) {
                log.info("Can't submit message for {} for now. Queue is full", topic);
                sleep(1000L);
            }
            sleep(500L);
        }
    }
}
