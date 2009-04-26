/**
 * 
 */
package ch.fhzh.info2.processor.impl.calc;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.Item;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;

/**
 * Document processor which calculates
 * total of all items found on document
 * 
 * @author bseelige
 *
 */
public class DocumentTotalizer implements DocumentProcessor {

 
    /* (non-Javadoc)
     * @see ch.fhzh.info2.processor.DocumentProcessor#processDocument(ch.fhzh.info2.document.Document)
     */
    public Document processDocument(Document doc)
            throws DocumentProcessorException {
        long total = 0;
        for (Item item : doc.getItems()) {
            /* guard against items in other
             * currency */
            if (!doc.getCur().equals(item.getCur())) {
                throw new DocumentProcessorException("currency does not match");
            }
            total += item.getCent();
        }
        doc.setTotalCent(total);
        return doc;
    }

}
