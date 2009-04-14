/**
 * 
 */
package ch.panter.edu.parser;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

/**
 * @author seb
 *
 */
public class HtmlParser02Test {
	
	HtmlParser instance;
	Reader reader;
	StringWriter writer;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.instance = new HtmlParser02();
		this.writer = new StringWriter();
	}

	/**
	 * Test method for {@link ch.panter.edu.parser.HtmlParser02#parse(java.io.Reader, java.io.Writer)}.
	 * @throws Exception 
	 */
	@Test
	public final void testParse() throws Exception {
		final String input = "<html></html>";
		reader = new StringReader(input);
		this.instance.parse(reader, writer);
		assertEquals("html", writer.getBuffer().toString().trim());
	}
	
	/**
	 * Test method for {@link ch.panter.edu.parser.HtmlParser02#parse(java.io.Reader, java.io.Writer)}.
	 * @throws Exception 
	 */
	@Test
	public final void testParseFixture() throws Exception {
		reader = new FileReader(new ClassPathResource("fixture02.in").getFile());
		this.instance.parse(reader, writer);		
		String expected = Util.readFileAsString(
		  new FileReader(new ClassPathResource("fixture02.expected").getFile()));
		assertEquals(expected, writer.toString());		
	}

}
