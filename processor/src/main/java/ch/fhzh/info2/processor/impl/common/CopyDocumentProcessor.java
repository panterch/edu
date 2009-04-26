/**
 * 
 */
package ch.fhzh.info2.processor.impl.common;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.DocumentImpl;
import ch.fhzh.info2.document.Item;
import ch.fhzh.info2.document.ItemImpl;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;

/**
 * Creates a new instance of document and
 * copies all values of submitted document onto
 * it to create an exact copy.
 * 
 * @author bseelige
 *
 */
public class CopyDocumentProcessor implements DocumentProcessor {

    /* (non-Javadoc)
     * @see ch.fhzh.info2.processor.DocumentProcessor#processDocument(ch.fhzh.info2.document.Document)
     */
    public Document processDocument(Document orig)
            throws DocumentProcessorException {
        Document copy = new DocumentImpl();
        copy.setId(orig.getId());
        // TODO copy all document fields
        
        if (null == orig.getItems()) {
            copy.setItems(null);
            return copy;
        }
        
        for (Item origItem : orig.getItems()) {
            Item copyItem = new ItemImpl();
            copyItem.setId(origItem.getId());
            // TODO copy all values from item
            copy.addItem(copyItem);
        }
        return copy;
    }

}
