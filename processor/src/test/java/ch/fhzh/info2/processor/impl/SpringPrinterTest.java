/**
 * 
 */
package ch.fhzh.info2.processor.impl;

import junit.framework.TestCase;
import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.impl.DocumentTestVo;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;
import ch.fhzh.info2.processor.impl.print.CommonsLangPrinter;
import ch.fhzh.info2.processor.impl.print.SpringPrinter;

/**
 * @author seb
 *
 */
public class SpringPrinterTest extends TestCase {
	
	private Document doc;
	private DocumentProcessor proc;
	
	protected void setUp() {
		this.doc = new DocumentTestVo();
		this.proc = new SpringPrinter();
	}
	
	public void testPrint() throws DocumentProcessorException {
		this.doc = this.proc.processDocument(this.doc);
		System.out.println(this.doc.getPrintRepresentation());
		
		// test some fields if found on document's string repr
		assertNotNull(doc.getPrintRepresentation());
		String repr = doc.getPrintRepresentation();
		assertTrue(repr.contains("TEST"));
		assertTrue(repr.contains("junit doc"));
		assertTrue(repr.contains("junit reference"));
	}

}
