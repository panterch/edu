package com.tddinaction.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class IO {

	public static byte[] readIntoByteArray(InputStream in) throws IOException {
		ByteArrayOutputStream content = new ByteArrayOutputStream();
		pipe(in, content);
		return content.toByteArray();
	}

	public static void pipe(InputStream source, OutputStream destination)
			throws IOException {
		int r = -1;
		byte[] buffer = new byte[8096];
		while ((r = source.read(buffer, 0, buffer.length)) != -1) {
			destination.write(buffer, 0, r);
		}
	}

	public static void pipe(Reader source, Writer destination)
			throws IOException {
		int r = -1;
		char[] buffer = new char[8096];
		while ((r = source.read(buffer, 0, buffer.length)) != -1) {
			destination.write(buffer, 0, r);
		}
	}

	public static String readIntoString(Reader reader) throws IOException {
		StringWriter writer = new StringWriter();
		pipe(reader, writer);
		return writer.toString();
	}
}
