package com.tddinaction.fit.fixtures;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookSetupFixture extends fitlibrary.SetUpFixture {
    public BookSetupFixture() throws Exception {
        super.registerParseDelegate(Date.class, new SimpleDateFormat(
                "yyyy-MM-dd"));
        deleteBookData();
    }

    private void deleteBookData() throws Exception {
        // implementation omitted for brevity
    }

    public void titleEditionISBNAuthors(String title, int edition,
            String isbn, String authors) throws Exception {
        // create a Book object from the arguments and
        // persist it to the system under test
    }
}
