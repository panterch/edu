/**
 * 
 */
package ch.fhzh.info2.processor.impl.clean;

import java.util.List;
import java.util.ArrayList;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.Item;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;

/**
 * Detects document items that do not match
 * the currency defined on document and
 * removes them
 * 
 * @author bseelige
 *
 */
public class CurrencyDefense implements DocumentProcessor {

    /* (non-Javadoc)
     * @see ch.fhzh.info2.processor.DocumentProcessor#processDocument(ch.fhzh.info2.document.Document)
     */
    public Document processDocument(Document doc)
            throws DocumentProcessorException {
        List<Item>valid = new ArrayList<Item>();
        for (Item item : doc.getItems()) {
            if (!doc.getCur().equals(item.getCur())) {
                continue;
            }
            valid.add(item);
        }
        doc.setItems(valid);
        return doc;
    }

}
