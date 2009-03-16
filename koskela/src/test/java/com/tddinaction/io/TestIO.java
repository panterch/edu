package com.tddinaction.io;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

public class TestIO {

	@Test
	public void testReadingFromStreamToByteArray() throws Exception {
		byte[] expected = "content".getBytes();
		ByteArrayInputStream input = new ByteArrayInputStream(expected);
		byte[] actual = IO.readIntoByteArray(input);
		assertEquals(new String(expected), new String(actual));
	}

	@Test
	public void testReadingFromReaderToString() throws Exception {
		String expected = "abc";
		assertEquals(expected, IO.readIntoString(new StringReader(expected)));
	}

	@Test
	public void testPipingStreams() throws Exception {
		String expected = "abc";
		InputStream source = new ByteArrayInputStream(expected.getBytes());
		OutputStream destination = new ByteArrayOutputStream();
		IO.pipe(source, destination);
		assertEquals(expected, destination.toString());
	}

	@Test
	public void testPipingReadersAndWriters() throws Exception {
		String expected = "abc";
		Reader source = new StringReader(expected);
		Writer destination = new StringWriter();
		IO.pipe(source, destination);
		assertEquals(expected, destination.toString());
	}
}
