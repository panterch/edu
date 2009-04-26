/**
 * 
 */
package ch.fhzh.info2.processor.impl.common;

import java.util.Calendar;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;

/**
 * Processor set's modification date on document to current
 * date.
 * 
 * @author seb
 *
 */
public class TouchDocumentProcessor implements DocumentProcessor {

	/* (non-Javadoc)
	 * @see ch.fhzh.info2.processor.DocumentProcessor#processDocument(ch.fhzh.info2.document.Document)
	 */
	public Document processDocument(Document doc)
			throws DocumentProcessorException {
		doc.setModTime(Calendar.getInstance());
		return doc;
	}

}
