package com.tddinaction.appendix.easymock;

import static org.easymock.EasyMock.*;
import org.easymock.EasyMock;
import org.junit.Test;

public class TestInternetRelayChat {

    @Test
    public void messagesAreOnlyDeliveredToOtherClients()
            throws Exception {
        String msg = "Maisk Maisk!";

        Client koskela = EasyMock.createMock(Client.class);
        Client freeman = EasyMock.createMock(Client.class);
        Client himberg = EasyMock.createMock(Client.class);

        expect(himberg.onMessage("lasse", msg)).andReturn(true);

        freeman.onMessage("lasse", msg);
        expectLastCall().andReturn(true);

        replay(freeman, koskela, himberg);

        InternetRelayChat irc = new InternetRelayChat();
        irc.join("inhuman", freeman);
        irc.join("vilbert", himberg);
        Prompt prompt = irc.join("lasse", koskela);
        prompt.say(msg);

        verify(freeman, koskela, himberg);
    }
}
