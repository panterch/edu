/**
 * 
 */
package ch.fhzh.info2.processor.impl.common;

import java.util.List;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;

/**
 * Processor which executes other processors
 * 
 * @author seb
 *
 */
public class ChainDocumentProcessor implements DocumentProcessor {

    /**
     * The list of processors.
     */
    private List<DocumentProcessor> processors;


	/**
	 * Processes the document through all configured processors.
     *
	 * @see ch.fhzh.info2.processor.DocumentProcessor#processDocument(ch.fhzh.info2.document.Document)
	 */
	public Document processDocument(Document doc)
			throws DocumentProcessorException {
		// guard no processors
		if (null == this.processors) {
			return doc;
		}
		for (DocumentProcessor delegate : this.processors) {
			doc = delegate.processDocument(doc);
		}
		return doc;
	}
	
	
    /**
     * @return Returns the processors.
     */
    public List<DocumentProcessor> getProcessors()
    {
        return processors;
    }

    /**
     * @param processors The processors to set.
     */
    public void setProcessors(List<DocumentProcessor> processors)
    {
        this.processors = processors;
    }


}
