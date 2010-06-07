package ch.fhzh.info2.currencyconverter;

public interface ICurrencyConverter {
	public Money convert(Money money, String destinationCurrency);
}
