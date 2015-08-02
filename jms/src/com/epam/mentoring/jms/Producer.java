package com.epam.mentoring.jms;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by Aliaksei Vihuro on 03.08.15.
 */
public class Producer {

    public static void main(String[] args) throws NamingException, JMSException {
        new Producer().run();
    }

    private void run() throws NamingException, JMSException {

        final Properties properties = getProperties();
        final InitialContext context = new InitialContext(properties);
        final ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
//        connectionFactory.createConnection("guest", "password");
        final Queue queue = (Queue) context.lookup("jms/myQueue");

        try (JMSContext jmsContext = connectionFactory.createContext("guest", "password")) {
            jmsContext.createProducer().send(queue, "Aliaksei Vihuro");
        }
    }

    private Properties getProperties() {

        final Properties properties = new Properties();

        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        properties.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
        properties.put(Context.SECURITY_PRINCIPAL, "admin");
        properties.put(Context.SECURITY_CREDENTIALS, "password");
        properties.put("jboss.naming.client.ejb.context", true);

        return properties;
    }
}
