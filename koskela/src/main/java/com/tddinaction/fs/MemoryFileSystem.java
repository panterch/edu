package com.tddinaction.fs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.tddinaction.io.IO;

public class MemoryFileSystem implements FileSystem {

	private Map<String, byte[]> files = new HashMap<String, byte[]>();

	public InputStream readAsStream(String path) {
		return new ByteArrayInputStream(files.get(path));
	}

	public OutputStream openForWrite(final String path) {
		return new ByteArrayOutputStream() {
			public void close() {
				files.put(path, toByteArray());
			}
		};
	}

	public void writeFile(String path, byte[] content) throws IOException {
		OutputStream out = openForWrite(path);
		out.write(content);
		out.close();
	}

	public void writeFile(String path, InputStream content) throws IOException {
		OutputStream out = openForWrite(path);
		IO.pipe(content, out);
		out.close();
	}

	public void writeFile(String path, Reader content) throws IOException {
		Writer out = new PrintWriter(openForWrite(path));
		IO.pipe(content, out);
		out.close();
	}

	public byte[] readFileAsBytes(String path) throws IOException {
		InputStream in = readAsStream(path);
		byte[] content = IO.readIntoByteArray(in);
		in.close();
		return content;
	}

	public InputStream readFileAsStream(String path) throws IOException {
		return readAsStream(path);
	}

	public Reader readFileAsReader(String path) throws IOException {
		return new InputStreamReader(readAsStream(path));
	}

	public boolean exists(String path) {
		return files.containsKey(path);
	}

	public void move(String from, String to) throws IOException {
		requireFileToExist(from);
		files.put(to, files.get(from));
		delete(from);
	}

	public void copy(String from, String to) throws IOException {
		requireFileToExist(from);
		files.put(to, files.get(from));
	}

	public void delete(String path) throws IOException {
		files.remove(path);
	}

	private void requireFileToExist(String path) throws IOException {
		if (!exists(path)) {
			throw new IOException("File " + path + " does not exist.");
		}
	}

}
