package ch.fhzh.info2.currencyconverter.impl;

import ch.fhzh.info2.currencyconverter.ICurrencyConverter;
import ch.fhzh.info2.currencyconverter.Money;

public class FactorCurrencyConverter implements ICurrencyConverter {
	private float factor;

	@Override
	public Money convert(Money money, String destinationCurrency) {
		return new Money(Math.round(money.getCents() * factor), destinationCurrency);
	}
	
	public float getFactor() {
		return factor;
	}

	public void setFactor(float factor) {
		this.factor = factor;
	}
}
