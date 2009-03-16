package com.tddinaction.j2ee.jms;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.After;
import org.junit.Before;

/**
 * An abstract base class for integration tests that require a JMS broker. The
 * base class provides access to fully functional QueueConnectionFactory and
 * TopicConnectionFactory, backed by an embedded ActiveMQ JMS broker. JUnit 4 is
 * required because of the use of Java 5 annotations.
 * 
 * @author Lasse Koskela
 */
public abstract class AbstractJmsTestCase {

    protected QueueConnectionFactory queueConnectionFactory;

    protected QueueConnection queueConnection;

    protected QueueSession queueSession;

    protected TopicConnectionFactory topicConnectionFactory;

    protected TopicConnection topicConnection;

    protected TopicSession topicSession;

    @Before
    public void setUpActiveMQ() throws Exception {
        disableActiveMqLogging();
        ActiveMQConnectionFactory broker = new ActiveMQConnectionFactory(
                "vm://localhost?broker.persistent=false");
        setupQueueConnection(broker);
        setupTopicConnection(broker);
    }

    @After
    public void tearDownActiveMQ() throws Exception {
        queueConnection.stop();
        queueConnection.close();
        topicConnection.stop();
        topicConnection.close();
    }

    private void setupQueueConnection(QueueConnectionFactory factory)
            throws Exception {
        queueConnectionFactory = factory;
        queueConnection = factory.createQueueConnection();
        queueConnection.start();
        queueSession = queueConnection.createQueueSession(false,
                Session.AUTO_ACKNOWLEDGE);
    }

    private void setupTopicConnection(TopicConnectionFactory factory)
            throws Exception {
        topicConnectionFactory = factory;
        topicConnection = factory.createTopicConnection();
        topicConnection.start();
        topicSession = topicConnection.createTopicSession(false,
                Session.AUTO_ACKNOWLEDGE);
    }

    private void disableActiveMqLogging() {
        Logger.getLogger("org.apache.activemq")
                .setLevel(Level.SEVERE);
    }
}
