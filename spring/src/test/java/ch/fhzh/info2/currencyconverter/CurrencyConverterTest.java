package ch.fhzh.info2.currencyconverter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CurrencyConverterTest extends TestCase {
	private ICurrencyConverter converter;
	public void setUp() {
		ApplicationContext context = new ClassPathXmlApplicationContext("converter.xml");
		converter = (ICurrencyConverter) context.getBean("staticConverter");
	}
	
	public void testCurrencyToString() {
		Money chf = new Money(1045, "CHF");
    Assert.assertTrue(chf.toString().matches("10[,\\.]45 CHF"));
	}
	
	public void testConverter() {
		Money chf = new Money(1045, "CHF");
		Money usd = converter.convert(chf, "USD");
		Assert.assertEquals("USD", usd.getCurrency());
		Assert.assertEquals(869, usd.getCents());
	}
}
