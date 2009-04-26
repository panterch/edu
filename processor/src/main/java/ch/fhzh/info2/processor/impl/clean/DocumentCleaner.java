/**
 * 
 */
package ch.fhzh.info2.processor.impl.clean;

import java.util.ArrayList;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.Item;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;

/**
 * Removes all items from the submitted document but
 * assures that item collection is initialized to
 * empty List.
 * 
 * @author bseelige
 *
 */
public class DocumentCleaner implements DocumentProcessor {


    /* (non-Javadoc)
     * @see ch.fhzh.info2.processor.DocumentProcessor#processDocument(ch.fhzh.info2.document.Document)
     */
    public Document processDocument(Document doc)
            throws DocumentProcessorException {
        if (null == doc.getItems()) {
            doc.setItems(new ArrayList<Item>());
        }
        doc.getItems().clear();
        return doc;
    }

}
