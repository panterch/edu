package com.tddinaction.ejb3.sessionbeans.calculator;

import java.rmi.RemoteException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

@Stateless
public class CalculatorBean implements Calculator, SessionBean {

    @Resource(mappedName = "QueueConnectionFactory")
    private QueueConnectionFactory queueConnectionFactory;

    @Resource(mappedName = "queue/testQueue", type = javax.jms.Queue.class)
    private Queue salesQueue;

    public int add(int a, int b) {
        notifyJmsQueue(a, b);
        return a + b;
    }

    private void notifyJmsQueue(int a, int b) {
        try {
            QueueConnection connection = queueConnectionFactory
                    .createQueueConnection();
            QueueSession session = connection.createQueueSession(
                    true, 10);
            QueueSender sender = session.createSender(salesQueue);
            TextMessage msg = session.createTextMessage();
            msg.setText("add(" + a + ", " + b + ")");
            sender.send(msg);
        } catch (Throwable e) {
        }
    }

    public void setSessionContext(SessionContext ctx)
            throws EJBException, RemoteException {
        System.out.println("setSessionContext()");
    }

    @PreDestroy
    public void ejbRemove() {
        System.out.println("@PreDestroy / ejbRemove");
    }

    @PostActivate
    public void ejbActivate() {
        System.out.println("@PostActivate / ejbActivate");
    }

    @PrePassivate
    public void ejbPassivate() {
        System.out.println("@PrePassivate / ejbRemove");
    }

    @PostConstruct
    public void ejbCreate() {
        System.out.println("@PostConstruct: " + toString());
    }

}
