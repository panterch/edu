package ch.fhzh.info2.processor.impl;

import java.util.Currency;
import java.util.Locale;

import junit.framework.TestCase;
import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.DocumentImpl;
import ch.fhzh.info2.document.Item;
import ch.fhzh.info2.document.ItemImpl;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;
import ch.fhzh.info2.processor.impl.calc.DocumentTotalizer;

public class DocumentTotalizerTest extends TestCase {

    Currency chf = Currency.getInstance(new Locale("de","CH"));
    
    /*
     * Test method for 'ch.fhzh.info2.processor.impl.DocumentTotalizer.processDocument(Document)'
     */
    public void testProcessDocument() throws DocumentProcessorException {
        /* create test document */
        Document doc = new DocumentImpl();
        doc.setCur(chf);
        Item item1 = new ItemImpl();
        item1.setCent(100);
        item1.setCur(chf);
        doc.addItem(item1);
        Item item2 = new ItemImpl();
        item2.setCent(200);
        item2.setCur(chf);
        doc.addItem(item2);
        
        /* process and check document */
        DocumentProcessor proc = new DocumentTotalizer();
        doc = proc.processDocument(doc);
        assertEquals(2, doc.getItems().size());
        assertEquals(300, doc.getTotalCent());
    }

}
