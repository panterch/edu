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

/**
 * @author seb
 *
 */
public class CommonsLangPrinterTest extends TestCase {
	
	private Document doc;
	private DocumentProcessor proc;
	
	protected void setUp() {
		this.doc = new DocumentTestVo();
		this.proc = new CommonsLangPrinter();
	}
	
	public void testPrint() throws DocumentProcessorException {
		this.doc = this.proc.processDocument(this.doc);
		//System.out.println(this.doc.getPrintRepresentation());
		
		// test some fields if found on document's string repr
		assertNotNull(doc.getPrintRepresentation());
		String repr = doc.getPrintRepresentation();
		assertTrue(repr.contains("type=TEST"));
		assertTrue(repr.contains("id=junit doc"));
		assertTrue(repr.contains("reference=junit reference"));
	}

}
