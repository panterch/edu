package com.tddinaction.ejb3.messagedriven;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/testQueue") })
public class TimerListenerBean implements MessageListener {

    @Resource
    public MessageDrivenContext ctx;

    @EJB(beanInterface = SearchService.class)
    public SearchService searchService;

    @Resource(mappedName = "QueueConnectionFactory")
    public QueueConnectionFactory connectionFactory;

    @Resource(mappedName = "queue/testQueue", type = javax.jms.Queue.class)
    public Queue resultsQueue;

    @PostConstruct
    public void ejbCreate() {
        System.out.println("MDB " + toString() + " created ("
                + (ctx != null ? "has" : "doesn't have")
                + " reference to MessageDrivenContext)");
    }

    public void onMessage(Message message) {
        try {
            String id = message.getJMSMessageID();
            System.out.println(toString() + " received JMS message "
                    + id + " and started timer");
            ctx.getTimerService().createTimer(5000, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Timeout
    public void onTimeout(Timer timer) {
        System.out.println(toString()
                + " received timeout callback: " + timer.getInfo());
    }

    public String toString() {
        String id = super.toString();
        return id.substring(id.lastIndexOf('@'));
    }

}
