package com.tddinaction.patterns.test;

public class ExampleOfTestSpecificSubclass {

    public class CreditCardProcessing {
        public boolean isValid(String cardnumber) {
            return validationCodeMatches(cardnumber)
                    && cardIsActive(cardnumber);
        }

        private boolean validationCodeMatches(String cardnumber) {
            return false;
        }

        private boolean cardIsActive(String cardnumber) {
            return false;
        }
    }
}
