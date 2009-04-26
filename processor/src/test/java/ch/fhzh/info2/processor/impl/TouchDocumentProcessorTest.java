/**
 * 
 */
package ch.fhzh.info2.processor.impl;

import java.util.Calendar;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.DocumentImpl;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;
import ch.fhzh.info2.processor.impl.common.TouchDocumentProcessor;
import junit.framework.TestCase;

/**
 * @author seb
 *
 */
public class TouchDocumentProcessorTest extends TestCase {

	/** instance under test */
	private DocumentProcessor instance;
	/** test document */
	private Document doc;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		instance = new TouchDocumentProcessor();
		doc = new DocumentImpl();
	}
	
	public void testTouch() throws DocumentProcessorException, InterruptedException {
		assertNull(doc.getModTime());
		doc = instance.processDocument(doc);
		Calendar cal1 = doc.getModTime();
		assertNotNull(cal1);
		Thread.currentThread().sleep(1);
		doc = instance.processDocument(doc);
		Calendar cal2 = doc.getModTime();
		assertNotNull(cal2);
		assertTrue(cal1.before(cal2));
	}

}
