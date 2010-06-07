package ch.fhzh.info2.currencyconverter.impl;

import ch.fhzh.info2.currencyconverter.ICurrencyConverter;
import ch.fhzh.info2.currencyconverter.Money;

public class NeutralCurrencyConverter implements ICurrencyConverter {

	@Override
	public Money convert(Money money, String destinationCurrency) {
		return new Money(money.getCents(), destinationCurrency);
	}

}
