package ch.fhzh.info2.processor.impl;

import java.util.Currency;
import java.util.Locale;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.DocumentImpl;
import ch.fhzh.info2.document.Item;
import ch.fhzh.info2.document.ItemImpl;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;
import ch.fhzh.info2.processor.impl.calc.DocumentTotalizer;
import ch.fhzh.info2.processor.impl.clean.CurrencyDefense;
import junit.framework.TestCase;

public class CurrencyDefenseTest extends TestCase {

    
   Currency chf = Currency.getInstance(new Locale("de","CH"));
   Currency eur = Currency.getInstance(new Locale("de","DE"));
    
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
        item2.setCur(eur);
        doc.addItem(item2);
        
        /* process and check document */
        DocumentProcessor proc = new CurrencyDefense();
        doc = proc.processDocument(doc);
        assertEquals(1, doc.getItems().size());
        assertEquals(chf, doc.getItems().get(0).getCur());
    }

}
