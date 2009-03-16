package com.tddinaction.appendix.easymock;

import java.util.HashMap;
import java.util.Map;

public class InternetRelayChat {

    private Map<String, Client> clients = new HashMap<String, Client>();

    public Prompt join(final String nickname, Client user) {
        clients.put(nickname, user);
        return new Prompt() {
            @Override
            public void say(String message) {
                deliverMessage(nickname, message);
            }
        };
    }

    protected void deliverMessage(String from, String message) {
        for (String nickname : clients.keySet()) {
            if (!nickname.equals(from)) {
                clients.get(nickname).onMessage(from, message);
            }
        }
    }
}
