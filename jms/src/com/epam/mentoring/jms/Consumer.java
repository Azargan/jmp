package com.epam.mentoring.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import java.util.logging.Logger;

/**
 * Created by Aliaksei Vihuro on 02.08.15.
 */
@MessageDriven(name = "Consumer", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/myQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class Consumer implements MessageListener {

    private static final Logger log = Logger.getLogger(Consumer.class.getName());

    @Override
    public void onMessage(Message message) {
        try {
            log.info("Hello, " + message.getBody(String.class) + "!");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
