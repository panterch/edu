package ch.fhzh.info2.currencyconverter.impl;

import java.util.HashMap;
import java.util.Map;

import ch.fhzh.info2.currencyconverter.ICurrencyConverter;
import ch.fhzh.info2.currencyconverter.Money;
import ch.fhzh.info2.currencyconverter.UnsupportedCurrencyConversionException;

public class StaticCurrencyConverter implements ICurrencyConverter {
	private static Map<String, Map<String, Float>> conversionRates;
	
	static {
		conversionRates = new HashMap<String, Map<String,Float>>();
		
		Map<String, Float> ratesChf = new HashMap<String, Float>();
		ratesChf.put("EUR", 0.7171f);
		ratesChf.put("USD", 0.8313f);
		conversionRates.put("CHF", ratesChf);
	}
	
	
	@Override
	public Money convert(Money money, String destinationCurrency) {
		Map<String, Float> rates = conversionRates.get(money.getCurrency());
		if (rates != null && rates.containsKey(destinationCurrency)) {
			Float rate = rates.get(destinationCurrency);
			return new Money(Math.round(rate * money.getCents()), destinationCurrency);
		} else {
			throw new UnsupportedCurrencyConversionException("Can not convert " + money.getCurrency() + " to " + destinationCurrency);
		}
	}

}
