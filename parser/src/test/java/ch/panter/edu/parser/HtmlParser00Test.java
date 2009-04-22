/**
 * 
 */
package ch.panter.edu.parser;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;

/**
 * @author seb
 *
 */
public class HtmlParser00Test {
	
	HtmlParser instance;
	StringReader reader;
	StringWriter writer;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.instance = new HtmlParser00();
		this.writer = new StringWriter();
	}

	/**
	 * Test method for {@link ch.panter.edu.parser.HtmlParser00#parse(java.io.Reader, java.io.Writer)}.
	 * @throws Exception 
	 */
	@Test
	public final void testParse() throws Exception {
		final String input = "<html><body>kiss!</body></html>";
		reader = new StringReader(input);
		this.instance.parse(reader, writer);
		assertEquals(input, writer.getBuffer().toString());
	}

}
