package com.tddinaction.ejb3.messagedriven;

import static org.easymock.EasyMock.aryEq;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.junit.Before;
import org.junit.Test;

public class SearchListenerBeanTest {

    private SearchService mockSearchService;

    private QueueConnectionFactory factory;

    private QueueConnection connection;

    private QueueSession session;

    private QueueSender sender;

    private Queue mockQueue;

    private ObjectMessage resultMessage;

    private ObjectMessage searchMessage;

    @Before
    public void setUp() {
        mockSearchService = createMock(SearchService.class);
        factory = createMock(QueueConnectionFactory.class);
        connection = createMock(QueueConnection.class);
        session = createMock(QueueSession.class);
        sender = createMock(QueueSender.class);
        mockQueue = createMock(Queue.class);
        resultMessage = createMock(ObjectMessage.class);
        searchMessage = createMock(ObjectMessage.class);
    }

    @Test
    public void searchRequestIsDelegatedAndResultsPassedForward() throws Exception {
        // non-mock input objects passed to the production code
        String[] keywords = new String[] { "keyword1", "keyword2" };
        String[] results = new String[] { "match1", "match2" };

        // expect the MDB to retrieve search request contents
        expect(searchMessage.getObject()).andReturn(keywords);

        // expect a call to the SearchService EJB
        expect(mockSearchService.search(aryEq(keywords))).andReturn(
                results);

        // expect the MDB to submit search results to the results queue
        expect(factory.createQueueConnection()).andReturn(connection);
        expect(
                connection.createQueueSession(false,
                        Session.AUTO_ACKNOWLEDGE)).andReturn(session);
        expect(session.createSender(mockQueue)).andReturn(sender);
        expect(session.createObjectMessage(aryEq(results)))
                .andReturn(resultMessage);
        sender.send(resultMessage);
        connection.close();

        // done recording expectations...
        replay(mockSearchService, factory, connection, session,
                mockQueue, sender, resultMessage, searchMessage);

        // ...ready to invoke business logic
        SearchListenerBean bean = new SearchListenerBean();
        bean.searchService = mockSearchService;
        bean.connectionFactory = factory;
        bean.resultsQueue = mockQueue;
        bean.onMessage(searchMessage);

        // verify expectations
        verify(mockSearchService, factory, connection, session,
                mockQueue, sender, resultMessage, searchMessage);
    }

}
