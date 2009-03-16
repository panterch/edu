package com.tddinaction.fs;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;

import com.tddinaction.io.IO;

public class TestDiskFileSystem extends FileSystemTestCase {

	private File mountPoint;

	@Override
	protected FileSystem getImplementation() {
		mountPoint = new File(System.getProperty("java.io.tmpdir"), "root");
		mountPoint.mkdir();
		return new DiskFileSystem(mountPoint);
	}

	@Test
	public void testRelativePathsAreRelativeToMountPoint() throws Exception {
		FileSystem fs = getImplementation();
		fs.writeFile("file.txt", "content".getBytes());
		File expectedFile = new File(mountPoint, "file.txt");
		FileInputStream stream = new FileInputStream(expectedFile);
		byte[] actual = IO.readIntoByteArray(stream);
		assertEquals("content", new String(actual));
	}

	@Test
	public void testAbsolutePathsAreAlsoRelativeToMountPoint() throws Exception {
		FileSystem fs = getImplementation();
		fs.writeFile("file.txt", "content".getBytes());
		File expectedFile = new File(mountPoint, "file.txt");
		FileInputStream stream = new FileInputStream(expectedFile);
		byte[] actual = IO.readIntoByteArray(stream);
		assertEquals("content", new String(actual));
	}
}
