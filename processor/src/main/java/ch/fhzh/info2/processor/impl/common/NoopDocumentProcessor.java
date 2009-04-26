/**
 * 
 */
package ch.fhzh.info2.processor.impl.common;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;

/**
 * Processor that does nothing on documents.
 * 
 * @author seb
 *
 */
public class NoopDocumentProcessor implements DocumentProcessor {

	/* (non-Javadoc)
	 * @see ch.fhzh.info2.processor.DocumentProcessor#processDocument(ch.fhzh.info2.document.Document)
	 */
	public Document processDocument(Document doc)
			throws DocumentProcessorException {
		return doc;
	}

}
