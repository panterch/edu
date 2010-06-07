package ch.fhzh.info2.currencyconverter;

public class Money {
	private String currency;
	private int cents;
	
	public Money(int cents, String currency) {
		this.cents = cents;
		this.currency = currency;
	}
	public String toString() {
		return String.format("%.2f %s", getAmount(), this.currency);
	}
	
	
	public int getCents() {
		return this.cents;
	}
	public String getCurrency() {
		return this.currency;
	}
	
	/**
	 * Getter for the amount in francs. Just used for displaying the Money.
	 */
	private float getAmount() {
		return this.cents / 100.0f;
	}
}
