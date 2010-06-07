package ch.fhzh.info2.currencyconverter.impl;

import ch.fhzh.info2.currencyconverter.ICurrencyConverter;
import ch.fhzh.info2.currencyconverter.Money;
import ch.fhzh.info2.currencyconverter.UnsupportedCurrencyConversionException;

public class ThrowingCurrencyConverter implements ICurrencyConverter {

	@Override
	public Money convert(Money money, String destinationCurrency) {
		throw new UnsupportedCurrencyConversionException("Please do not use me in Production code. I'm just here to test error cases");
	}

}
