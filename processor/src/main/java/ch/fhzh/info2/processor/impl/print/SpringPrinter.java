/**
 * 
 */
package ch.fhzh.info2.processor.impl.print;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.core.style.ToStringCreator;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;

/**
 * Document processor that uses spring
 * to build document string representation
 * 
 * @author seb
 *
 */
public class SpringPrinter implements DocumentProcessor {

	/* (non-Javadoc)
	 * @see ch.fhzh.info2.processor.DocumentProcessor#processDocument(ch.fhzh.info2.document.Document)
	 */
	public Document processDocument(Document doc)
			throws DocumentProcessorException {
		ToStringCreator creator =  new ToStringCreator(doc);
		creator.append("id", doc.getId());
		creator.append("type", doc.getType());
		creator.append("reference", doc.getReference());
		doc.setPrintRepresentation(creator.toString());
		return doc;
	}

}
