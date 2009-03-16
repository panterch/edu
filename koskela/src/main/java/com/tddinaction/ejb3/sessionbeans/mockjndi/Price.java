package com.tddinaction.ejb3.sessionbeans.mockjndi;

public class Price {

    private int cents;

    public Price(int cents) {
        this.cents = cents;
    }

    @Override
    public int hashCode() {
        return cents;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Price)) {
            return false;
        }
        Price other = (Price) obj;
        return other.cents == this.cents;
    }

    @Override
    public String toString() {
        return "Price[" + cents + "]";
    }
}
