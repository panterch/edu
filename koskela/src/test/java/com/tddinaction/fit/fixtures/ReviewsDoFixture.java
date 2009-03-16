package com.tddinaction.fit.fixtures;

import fit.Fixture;
import fitlibrary.DoFixture;

public class ReviewsDoFixture extends DoFixture {
    public Fixture setupBookData() throws Exception {
        return new BookSetupFixture();
    }

    public Fixture setupReviewData() throws Exception {
        return new ReviewSetupFixture();
    }
    
    public boolean goToFrontPage() {
        return true;
    }
    
    public boolean textPresent(String expected) {
        return true;
    }
    
    public boolean verifyThatIsBefore(String first, String second) {
        return true;
    }
}
