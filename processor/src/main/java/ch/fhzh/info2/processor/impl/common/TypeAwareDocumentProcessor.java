/**
 * 
 */
package ch.fhzh.info2.processor.impl.common;

import java.util.Map;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;

/**
 * A document type aware processor for Documents
 * <p>
 * A processor that delegetas its work to other processors,
 * which get are getting injected by the container. This
 * processor is mostly used as main processors which calls
 * other processors on behalf of the submitted command.
 * </p>
 * @author seb
 *
 */
public class TypeAwareDocumentProcessor implements DocumentProcessor {

	/**
     * A default processor - if specified, this processor is invoked in case no
     * processor has been defined matching the command.
     */
    private DocumentProcessor defaultProcessor;

    /**
     * A map of processors mapped to the processing type.
     */
    private Map<String, DocumentProcessor> typeProcessors;


	/* (non-Javadoc)
	 * @see ch.fhzh.info2.processor.DocumentProcessor#processDocument(ch.fhzh.info2.document.Document)
	 */
	public Document processDocument(Document doc)
			throws DocumentProcessorException {
        Document.Type dt = doc.getType();

        // use a specific processor for the document type?
        if (this.typeProcessors.containsKey(dt.name())) {
            return this.typeProcessors.get(dt.name()).processDocument(doc);
        }

        // delegate to default processor if present
        if (this.defaultProcessor != null) {
            return this.defaultProcessor.processDocument(doc);
        }
        
        throw new DocumentProcessorException("please specify at least one processor");

	}
	
    /**
     * @return Returns the typeProcessors.
     */
    public Map<String, DocumentProcessor> getTypeProcessors() {
        return typeProcessors;
    }

    /**
     * @param typeProcessors The typeProcessors to set.
     */
    public void setTypeProcessors(Map<String, DocumentProcessor> typeProcessors) {
        this.typeProcessors = typeProcessors;
    }

    public DocumentProcessor getDefaultProcessor() {
        return defaultProcessor;
    }

    public void setDefaultProcessor(DocumentProcessor defaultProcessor) {
        this.defaultProcessor = defaultProcessor;
    }

}
