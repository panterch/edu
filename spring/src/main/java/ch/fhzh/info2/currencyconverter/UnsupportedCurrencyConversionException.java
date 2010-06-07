package ch.fhzh.info2.currencyconverter;

public class UnsupportedCurrencyConversionException extends RuntimeException {
	public UnsupportedCurrencyConversionException(String message) {
		super(message);
	}
}
