package com.tddinaction.j2ee.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

public class SearchListenerBean implements MessageListener {

    public SearchService searchService;

    public QueueConnectionFactory connectionFactory;

    public Queue resultsQueue;

    public void onMessage(Message message) {
        try {
            ObjectMessage searchRequest = (ObjectMessage) message;
            String[] keywords = (String[]) searchRequest.getObject();
            String[] results = searchService.search(keywords);

            QueueConnection connection = connectionFactory
                    .createQueueConnection();
            QueueSession session = connection.createQueueSession(
                    false, Session.AUTO_ACKNOWLEDGE);
            QueueSender sender = session.createSender(resultsQueue);
            message = session.createObjectMessage(results);
            sender.send(message);
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
