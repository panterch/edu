/**
 * 
 */
package ch.fhzh.info2.processor;

import ch.fhzh.info2.document.Document;

/**
 * Document processor service
 * 
 * @author bseelige
 *
 */
public interface DocumentProcessor {
    
    /**
     * Applies processing algorithm to
     * document
     * 
     * @param doc the document to process 
     * @return (maybe new) instance of document
     */
    public Document processDocument(Document doc) throws DocumentProcessorException;

}
