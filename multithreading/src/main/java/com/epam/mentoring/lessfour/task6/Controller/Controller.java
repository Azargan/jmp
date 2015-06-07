package com.epam.mentoring.lessfour.task6.Controller;

import com.epam.mentoring.lessfour.task6.message.Message;

/**
 *
 * @author Aliaksei Vihuro <aliaksei_vihuro@epam.com>
 */
public interface Controller {
    public Boolean submit(final Message message);
    public String getMessage(final String topic);
}
