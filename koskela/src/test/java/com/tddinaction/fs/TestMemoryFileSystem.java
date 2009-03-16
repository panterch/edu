package com.tddinaction.fs;

import org.junit.Test;

public class TestMemoryFileSystem extends FileSystemTestCase {

	@Override
	protected MemoryFileSystem getImplementation() {
		return new MemoryFileSystem();
	}

	@Test
	public void test() {
	}
}
