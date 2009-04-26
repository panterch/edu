/**
 * 
 */
package ch.fhzh.info2.processor.impl.print;

import org.apache.commons.lang.builder.ToStringBuilder;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;

/**
 * Document processor that uses commons lang
 * to build document string representation
 * 
 * @author seb
 *
 */
public class CommonsLangPrinter implements DocumentProcessor {

	/* (non-Javadoc)
	 * @see ch.fhzh.info2.processor.DocumentProcessor#processDocument(ch.fhzh.info2.document.Document)
	 */
	public Document processDocument(Document doc)
			throws DocumentProcessorException {
		 String repr =  ToStringBuilder.reflectionToString(doc);
		 doc.setPrintRepresentation(repr);
		 return doc;
	}

}
