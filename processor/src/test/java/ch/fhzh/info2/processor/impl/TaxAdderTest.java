package ch.fhzh.info2.processor.impl;

import junit.framework.TestCase;
import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.DocumentImpl;
import ch.fhzh.info2.processor.DocumentProcessorException;
import ch.fhzh.info2.processor.impl.calc.TaxAdder;

public class TaxAdderTest extends TestCase {

    /*
     * Test method for 'ch.fhzh.info2.processor.impl.TaxAdder.processDocument(Document)'
     */
    public void testProcessDocument() throws DocumentProcessorException {
        Document doc = new DocumentImpl();
        doc.setTotalCent(100);
        TaxAdder proc = new TaxAdder();
        proc.setTaxRate(0.079d);
        doc = proc.processDocument(doc);
        assertEquals(108, doc.getTotalCent());
    }

}
