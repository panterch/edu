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
public class HtmlParser01Test {
	
	HtmlParser instance;
	StringReader reader;
	StringWriter writer;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.instance = new HtmlParser01();
		this.writer = new StringWriter();
	}

	/**
	 * Test method for {@link ch.panter.edu.parser.HtmlParser01#parse(java.io.Reader, java.io.Writer)}.
	 * @throws Exception 
	 */
	@Test
	public final void testParse() throws Exception {
		final String input = "<html><body>kiss!</body></html>";
		reader = new StringReader(input);
		this.instance.parse(reader, writer);
		assertEquals("kiss!", writer.getBuffer().toString());
	}
	
	/**
	 * Test method for {@link ch.panter.edu.parser.HtmlParser01#parse(java.io.Reader, java.io.Writer)}.
	 * @throws Exception 
	 */
	@Test
	public final void testParseHasLimitations() throws Exception {
		final String input = "<html><body>kiss!<p>xyz</p></body></html>";
		reader = new StringReader(input);
		this.instance.parse(reader, writer);
		assertEquals("kiss!", writer.getBuffer().toString());
	}

}
