package com.tddinaction.j2ee.jms;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueReceiver;

import org.junit.Before;
import org.junit.Test;

public class TestActiveMq extends AbstractJmsTestCase implements
        MessageListener, SearchService {

    private String[] searchKeywords = new String[] { "needle" };

    private String[] resultsFromSearchService = new String[] { "one",
            "two" };

    private String[] submittedSearchResults;

    private CountDownLatch resultsReceived;

    @Before
    public void createSynchronizers() throws Exception {
        resultsReceived = new CountDownLatch(1);
    }

    public void onMessage(Message message) {
        try {
            submittedSearchResults = (String[]) ((ObjectMessage) message)
                    .getObject();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
        resultsReceived.countDown();
    }

    public String[] search(String[] keywords) {
        List<String> expected = Arrays.asList(searchKeywords);
        List<String> actual = Arrays.asList(keywords);
        assertEquals("Wrong search keywords.", expected, actual);
        return resultsFromSearchService;
    }

    @Test
    public void testSearchResultsAreSubmittedToResultsQueue()
            throws Exception {
        Queue resultsQueue = queueSession.createQueue("Results");
        QueueReceiver receiver = queueSession
                .createReceiver(resultsQueue);
        receiver.setMessageListener(this);

        SearchListenerBean bean = new SearchListenerBean();
        bean.connectionFactory = queueConnectionFactory;
        bean.resultsQueue = resultsQueue;
        bean.searchService = this;

        bean.onMessage(queueSession
                .createObjectMessage(searchKeywords));

        resultsReceived.await(1, TimeUnit.SECONDS);
        assertEquals(Arrays.asList(resultsFromSearchService), Arrays
                .asList(submittedSearchResults));
    }

    @Test
    public void test2() throws Exception {
        testSearchResultsAreSubmittedToResultsQueue();
    }

    @Test
    public void test3() throws Exception {
        testSearchResultsAreSubmittedToResultsQueue();
    }

    @Test
    public void test4() throws Exception {
        testSearchResultsAreSubmittedToResultsQueue();
    }

    @Test
    public void test5() throws Exception {
        testSearchResultsAreSubmittedToResultsQueue();
    }

    @Test
    public void test6() throws Exception {
        testSearchResultsAreSubmittedToResultsQueue();
    }

    @Test
    public void test7() throws Exception {
        testSearchResultsAreSubmittedToResultsQueue();
    }

    @Test
    public void test8() throws Exception {
        testSearchResultsAreSubmittedToResultsQueue();
    }

    @Test
    public void test9() throws Exception {
        testSearchResultsAreSubmittedToResultsQueue();
    }

    @Test
    public void test10() throws Exception {
        testSearchResultsAreSubmittedToResultsQueue();
    }
}
