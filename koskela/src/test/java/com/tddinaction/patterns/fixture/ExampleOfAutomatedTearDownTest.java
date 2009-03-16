package com.tddinaction.patterns.fixture;

import static org.easymock.EasyMock.expect;

import org.junit.Test;

import com.tddinaction.appendix.easymock.Client;
import com.tddinaction.appendix.easymock.InternetRelayChat;
import com.tddinaction.appendix.easymock.Prompt;

public class ExampleOfAutomatedTearDownTest extends
        AutomatedTeardownTestCase {

    @Test
    public void messagesAreOnlyDeliveredToOtherClients()
            throws Exception {
        String msg = "Maisk Maisk!";

        Client alex = (Client) createMock(Client.class);
        Client bill = (Client) createMock(Client.class);
        Client cory = (Client) createMock(Client.class);

        expect(cory.onMessage("alex", msg)).andReturn(true);
        expect(bill.onMessage("alex", msg)).andReturn(true);
        replayAll();

        InternetRelayChat irc = new InternetRelayChat();
        irc.join("bill", bill);
        irc.join("cory", cory);
        Prompt prompt = irc.join("alex", alex);
        prompt.say(msg);
    }
}
