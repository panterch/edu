package com.tddinaction.fit.fixtures;

import java.text.SimpleDateFormat;
import java.util.Date;

import fitlibrary.SetUpFixture;

public class ReviewSetupFixture extends SetUpFixture {
    public ReviewSetupFixture() throws Exception {
        super.registerParseDelegate(Date.class, new SimpleDateFormat(
                "yyyy-MM-dd"));
        deleteReviewData();
    }

    private void deleteReviewData() throws Exception {
        // implementation omitted for brevity
    }

    public void titleEditionReviewDateReviewText(String title,
            int edition, Date date, String text) throws Exception {
        // create a Review object from the arguments and
        // persist it to the system under test
    }
}
