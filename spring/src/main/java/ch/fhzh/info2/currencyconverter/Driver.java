package ch.fhzh.info2.currencyconverter;

import ch.fhzh.info2.currencyconverter.impl.StaticCurrencyConverter;

public class Driver {
	public static void main(String[] args) {
		ICurrencyConverter converter = new StaticCurrencyConverter();
		Money chf = new Money(100, "CHF");
		Money usd = converter.convert(chf, "USD");
		System.out.println("Converted " + chf + " to " + usd);
	}
}
