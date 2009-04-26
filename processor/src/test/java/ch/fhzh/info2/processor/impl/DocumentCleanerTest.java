package ch.fhzh.info2.processor.impl;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.DocumentImpl;
import ch.fhzh.info2.document.ItemImpl;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;
import ch.fhzh.info2.processor.impl.clean.DocumentCleaner;
import junit.framework.TestCase;

public class DocumentCleanerTest extends TestCase {
    
    /** instance under test */
    DocumentProcessor instance;
    
    /** value object for tests */
    Document doc;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DocumentCleanerTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        instance = new DocumentCleaner();
        doc = new DocumentImpl();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'ch.fhzh.info2.processor.impl.DocumentCleaner.processDocument(Document)'
     */
    public void testProcessDocument() throws DocumentProcessorException {
        doc.addItem(new ItemImpl());
        assertEquals("not properly initialized", 1, doc.getItems().size());
        doc = instance.processDocument(doc);
        assertNotNull("no document returned", doc);
        assertEquals("not processed", 0, doc.getItems().size());   
    }

    /*
     * Test method for 'ch.fhzh.info2.processor.impl.DocumentCleaner.processDocument(Document)'
     */
    public void testProcessDocumentNoItems() throws DocumentProcessorException {
        doc.setItems(null);
        doc = instance.processDocument(doc);
        assertNotNull("no document returned", doc);
        assertEquals("not processed", 0, doc.getItems().size());   
    }
}
