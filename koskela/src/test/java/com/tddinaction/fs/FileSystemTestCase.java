package com.tddinaction.fs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tddinaction.io.IO;

public abstract class FileSystemTestCase {

	protected FileSystem fs;

	protected String path1;

	protected String path2;

	protected byte[] content;

	protected abstract FileSystem getImplementation();

	@Before
	public void setUp() throws Exception {
		fs = getImplementation();
		path1 = "path/1.txt";
		path2 = "path/2.txt";
		content = "content".getBytes();
	}

	@After
	public void tearDown() throws Exception {
		if (fs.exists(path1)) {
			fs.delete(path1);
		}
		if (fs.exists(path2)) {
			fs.delete(path2);
		}
	}

	@Test
	public void testFileSystemReadsAndWritesByteArrays() throws Exception {
		fs.writeFile(path1, content);
		byte[] read = fs.readFileAsBytes(path1);
		Assert.assertEquals(new String(content), new String(read));
	}

	@Test
	public void testFileSystemReadsAndWritesStreams() throws Exception {
		fs.writeFile(path1, new ByteArrayInputStream(content));
		byte[] read = IO.readIntoByteArray(fs.readFileAsStream(path1));
		Assert.assertEquals(new String(content), new String(read));
	}

	@Test
	public void testFileSystemReadsAndWritesReaders() throws Exception {
		String content = "content";
		fs.writeFile(path1, new StringReader(content));
		String read = IO.readIntoString(fs.readFileAsReader(path1));
		Assert.assertEquals(content, read);
	}

	@Test
	public void testFileSystemKnowsWhichFilesExist() throws Exception {
		fs.writeFile(path1, "content".getBytes());
		Assert.assertTrue(fs.exists(path1));
		Assert.assertFalse(fs.exists(path2));
	}

	@Test
	public void testDeletingFiles() throws Exception {
		fs.writeFile(path1, "content".getBytes());
		fs.delete(path1);
		Assert.assertFalse(fs.exists(path1));
	}

	@Test
	public void testMovingFiles() throws Exception {
		fs.writeFile(path1, content);
		Assert.assertTrue(fs.exists(path1));
		Assert.assertFalse(fs.exists(path2));

		fs.move(path1, path2);
		Assert.assertFalse(fs.exists(path1));
		Assert.assertTrue(fs.exists(path2));
	}

	@Test
	public void testCopyingFiles() throws Exception {
		fs.writeFile(path1, content);
		Assert.assertTrue(fs.exists(path1));
		Assert.assertFalse(fs.exists(path2));

		fs.copy(path1, path2);
		Assert.assertTrue(fs.exists(path1));
		Assert.assertTrue(fs.exists(path2));
	}

	@Test
	public void testFilesAreOverwrittenWhenMovingSomethingOverThem()
			throws Exception {
		fs.writeFile(path1, content);
		fs.writeFile(path2, "original".getBytes());
		Assert.assertTrue(fs.exists(path1));
		Assert.assertTrue(fs.exists(path2));

		fs.move(path1, path2);
		Assert.assertFalse(fs.exists(path1));
		Assert.assertTrue(fs.exists(path2));
		byte[] read = fs.readFileAsBytes(path2);
		Assert.assertEquals(new String(content), new String(read));
	}

	@Test(expected = IOException.class)
	public void testMovingNonExistentFileThrowsException() throws Exception {
		fs.move("nonexistent.txt", "to.txt");
	}

	@Test(expected = IOException.class)
	public void testCopyingNonExistentFileThrowsException() throws Exception {
		fs.copy("nonexistent.txt", "to.txt");
	}

}
