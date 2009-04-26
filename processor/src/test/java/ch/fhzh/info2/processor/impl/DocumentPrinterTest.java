package ch.fhzh.info2.processor.impl;

import junit.framework.TestCase;
import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.DocumentImpl;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;
import ch.fhzh.info2.processor.impl.print.DocumentPrinter;

/**
 * 
 * @author bseelige
 */
public class DocumentPrinterTest extends TestCase {
    
    DocumentProcessor instance;
    Document doc;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DocumentPrinterTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        instance = new DocumentPrinter();
        doc = new DocumentImpl();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'ch.fhzh.info2.processor.impl.DocumentPrinter.processDocument(Document)'
     */
    public void testProcessDocument() throws DocumentProcessorException {
        assertNotNull("no document", doc);
        assertNotNull("no instance", instance);
        doc.setId("junit");
        doc = instance.processDocument(doc);
        assertNotNull("no document returned", doc);
        String repr = doc.getPrintRepresentation();
        assertNotNull("no representation on document", repr);
        assertTrue("id not found",repr.contains("junit"));
    }
    



}
