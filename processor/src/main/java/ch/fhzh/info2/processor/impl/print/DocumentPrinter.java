/**
 * 
 */
package ch.fhzh.info2.processor.impl.print;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.Item;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;

/**
 * Adds / updates content of print representation
 * field on documents submitted
 * 
 * @author bseelige
 *
 */
public class DocumentPrinter implements DocumentProcessor {


    /* (non-Javadoc)
     * @see ch.fhzh.info2.processor.DocumentProcessor#processDocument(ch.fhzh.info2.document.Document)
     */
    public Document processDocument(Document doc)
            throws DocumentProcessorException {
       
        StringBuilder repr = new StringBuilder();
        repr.append(doc.getClass().getSimpleName());
        repr.append(' ');
        if (null != doc.getId()) {
            repr.append(doc.getId());
            repr.append(' ');
        }
        if (null != doc.getType()) {
        	repr.append(doc.getType());
            repr.append(' ');    
        }
        if (null != doc.getModTime()) {
        	repr.append('\n');
        	repr.append(doc.getModTime().getTime());
        }
        repr.append('\n');
        for (Item item : doc.getItems()) {
        	repr.append('\n');
        	repr.append(item.getId());
        	repr.append(' ');
        	repr.append(item.getDesc());
        	repr.append(' ');
        	repr.append(item.getCent());
        	repr.append(item.getCur());
        }
        repr.append('\n');
        repr.append('\n');
        repr.append("Total:");
        repr.append(doc.getTotalCent());
    	repr.append(doc.getCur());
        doc.setPrintRepresentation(repr.toString());
        
        return doc;
    }

}
