package com.epam.mentoring.lessfour.task6.Controller;

import com.epam.mentoring.lessfour.task6.message.Message;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aliaksei Vihuro <aliaksei_vihuro@epam.com>
 */
public class SimpleController implements Controller {

    private Integer counter = 0;
    private final Integer queueMaxLength;
    private final Map<String, List<String>> queue;

    public SimpleController(final Integer queueLength) throws Exception {
        if (queueLength <= 0) {
            throw new Exception("Controller can't have less than 1 queue length");
        }
        this.queueMaxLength = queueLength;
        this.queue = Collections.synchronizedMap(Maps.newHashMapWithExpectedSize(queueLength));
    }

    @Override
    public Boolean submit(Message message) {
        synchronized (queue) {
            if (counter.equals(queueMaxLength)) {
                return false;
            }
            List<String> messages = queue.get(message.getTopic());
            if (messages == null) {
                messages = Collections.synchronizedList(new LinkedList<>());
            }
            messages.add(message.getMessage());
            queue.put(message.getTopic(), messages);
            counter++;
        }
        return true;
    }

    @Override
    public String getMessage(String topic) {
        String message;
        synchronized (queue) {
            if (!queue.containsKey(topic)) {
                return null;
            }
            final List<String> messages = queue.get(topic);
            if (messages.isEmpty()) {
                return null;
            }
            message = messages.get(0);
            messages.remove(message);
            counter--;
        }
        return message;
    }
}
