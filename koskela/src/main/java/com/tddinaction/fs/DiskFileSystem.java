package com.tddinaction.fs;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.tddinaction.io.IO;

public class DiskFileSystem implements FileSystem {

	private File mountPoint;

	public DiskFileSystem(File mountPoint) {
		this.mountPoint = mountPoint;
	}

	public void copy(String from, String to) throws IOException {
		requireExists(from);
		writeFile(to, readFileAsBytes(from));
	}

	public void delete(String path) throws IOException {
		mapToFile(path).delete();
	}

	public boolean exists(String path) {
		return mapToFile(path).exists();
	}

	public void move(String from, String to) throws IOException {
		requireExists(from);
		mapToFile(from).renameTo(mapToFile(to));
	}

	private void requireExists(String path) throws IOException {
		if (!mapToFile(path).exists()) {
			throw new IOException(path + " does not exist.");
		}
	}

	public byte[] readFileAsBytes(String path) throws IOException {
		File target = mapToFile(path);
		FileInputStream src = new FileInputStream(target);
		byte[] content = IO.readIntoByteArray(src);
		src.close();
		return content;
	}

	public Reader readFileAsReader(String path) throws IOException {
		return new InputStreamReader(readFileAsStream(path));
	}

	public InputStream readFileAsStream(String path) throws IOException {
		File target = mapToFile(path);
		FileInputStream src = new FileInputStream(target);
		byte[] content = IO.readIntoByteArray(src);
		src.close();
		return new ByteArrayInputStream(content);
	}

	public void writeFile(String path, byte[] content) throws IOException {
		writeFile(path, new ByteArrayInputStream(content));
	}

	public void writeFile(String path, InputStream content) throws IOException {
		File target = mapToFile(path);
		target.getParentFile().mkdirs();
		FileOutputStream out = new FileOutputStream(target);
		IO.pipe(content, out);
		out.close();
	}

	public void writeFile(String path, Reader content) throws IOException {
		File target = mapToFile(path);
		target.getParentFile().mkdirs();
		FileWriter out = new FileWriter(target);
		IO.pipe(content, out);
		out.close();
	}

	private File mapToFile(String path) {
		return new File(mountPoint, path);
	}

}
