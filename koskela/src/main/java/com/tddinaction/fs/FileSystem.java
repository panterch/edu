package com.tddinaction.fs;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public interface FileSystem {

	void writeFile(String path, byte[] content) throws IOException;

	void writeFile(String path, InputStream content) throws IOException;

	void writeFile(String path, Reader content) throws IOException;

	byte[] readFileAsBytes(String path) throws IOException;

	InputStream readFileAsStream(String path) throws IOException;

	boolean exists(String path);

	void move(String from, String to) throws IOException;

	void copy(String from, String to) throws IOException;

	void delete(String path) throws IOException;

	Reader readFileAsReader(String path) throws IOException;

}
