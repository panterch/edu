package ch.fhzh.info2.currencyconverter;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/converter.xml")
public class SpringCurrencyConverterTest {
	@Autowired
	private ICurrencyConverter staticConverter;
	@Test
	public void testConverter() {
		Money chf = new Money(1045, "CHF");
		Money usd = staticConverter.convert(chf, "USD");
		Assert.assertEquals("USD", usd.getCurrency());
		Assert.assertEquals(869, usd.getCents());
	}
	
}
