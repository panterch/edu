package ch.fhzh.info2.processor.impl;

import junit.framework.TestCase;
import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.DocumentImpl;
import ch.fhzh.info2.document.Item;
import ch.fhzh.info2.document.ItemImpl;
import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.DocumentProcessorException;
import ch.fhzh.info2.processor.impl.common.CopyDocumentProcessor;

public class CopyDocumentProcessorTest extends TestCase {

    /** instance under test */
    private DocumentProcessor instance;
    
    /** document used for tests */
    private Document doc;
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(CopyDocumentProcessorTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        instance = new CopyDocumentProcessor();
        doc = new DocumentImpl();
        Item item = new ItemImpl();
        item.setId("123");
        doc.addItem(item);
        doc.setId("junit doc");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'ch.fhzh.info2.processor.impl.CopyDocumentProcessor.processDocument(Document)'
     */
    public void testProcessDocument() throws DocumentProcessorException {
        Document doc2 = instance.processDocument(doc);
        assertNotNull("no document returned", doc2);
        assertNotSame("same document returned", doc, doc2);
        assertEquals(doc.getId(), doc2.getId());
        assertEquals(doc.getItems().size(), doc2.getItems().size());
    }

    /*
     * Test method for 'ch.fhzh.info2.processor.impl.CopyDocumentProcessor.processDocument(Document)'
     */
    public void testProcessDocumentNull() throws DocumentProcessorException {
        doc.setItems(null);
        Document doc2 = instance.processDocument(doc);
        assertNotNull("no document returned", doc2);
        assertNotSame("same document returned", doc, doc2);
        assertEquals(doc.getId(), doc2.getId());
        assertNull(doc2.getItems());
    }

}
