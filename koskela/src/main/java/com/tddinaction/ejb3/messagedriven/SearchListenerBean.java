package com.tddinaction.ejb3.messagedriven;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/testQueue") })
public class SearchListenerBean implements MessageListener {

    @EJB(beanInterface = SearchService.class)
    public SearchService searchService;

    @Resource(mappedName = "QueueConnectionFactory")
    public QueueConnectionFactory connectionFactory;

    @Resource(mappedName = "queue/testQueue", type = javax.jms.Queue.class)
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

            Message resultsMessage = session
                    .createObjectMessage(results);
            sender.send(resultsMessage);

            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
