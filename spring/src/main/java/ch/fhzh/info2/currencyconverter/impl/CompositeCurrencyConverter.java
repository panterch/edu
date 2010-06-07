package ch.fhzh.info2.currencyconverter.impl;

import java.util.List;

import ch.fhzh.info2.currencyconverter.ICurrencyConverter;
import ch.fhzh.info2.currencyconverter.Money;

public class CompositeCurrencyConverter implements ICurrencyConverter {
	private List<ICurrencyConverter> converters;
	
	@Override
	public Money convert(Money money, String destinationCurrency) {
		Money converted = money;
		for (ICurrencyConverter converter : this.converters) {
			converted = converter.convert(converted, destinationCurrency);
		}
		return converted;
	}

	public List<ICurrencyConverter> getConverters() {
		return converters;
	}

	public void setConverters(List<ICurrencyConverter> converters) {
		this.converters = converters;
	}
}
