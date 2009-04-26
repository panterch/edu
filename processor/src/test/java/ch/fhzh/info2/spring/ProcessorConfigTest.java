package ch.fhzh.info2.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import ch.fhzh.info2.processor.DocumentProcessor;
import ch.fhzh.info2.processor.impl.calc.TaxAdder;
import ch.fhzh.info2.processor.impl.common.ChainDocumentProcessor;
import ch.fhzh.info2.processor.impl.email.EmailSendingProcessor;

import junit.framework.TestCase;

/**
 * Tests all beans defined in processor.xml
 * @author bseelige
 *
 */
public class ProcessorConfigTest extends TestCase {
    
    /** factory instance under test */
    BeanFactory factory;

    protected void setUp() throws Exception {
        super.setUp();
        ClassPathResource resource = new ClassPathResource("processor.xml");
        factory = new XmlBeanFactory(resource);
    }
    
    public void testTotalizer() {
        checkProcessor("totalizer");
    }

    public void testChTaxAdder() {
        TaxAdder proc = (TaxAdder)checkProcessor("chTaxAdder");
        assertEquals(0.079d, proc.getTaxRate());
    }
    
    public void testDeTaxAdder() {
        TaxAdder proc = (TaxAdder)checkProcessor("deTaxAdder");
        assertEquals(0.19d, proc.getTaxRate());
    }
    
    public void testEmail() {
    	EmailSendingProcessor proc = (EmailSendingProcessor)checkProcessor("email");
        assertNotNull(proc.getFrom());
        assertNotNull(proc.getHostName());
        assertNotNull(proc.getTo());
    }
    
    public void testCurrencyDefense() {
        checkProcessor("currencyDefense");
    }
    
    public void testCleaner() {
        checkProcessor("cleaner");
    }
    
    public void testCopy() {
        checkProcessor("copy");
    }
    
    public void testNoop() {
        checkProcessor("noop");
    }
    
    public void testCommonsLangPrinter() {
        checkProcessor("commonsLangPrinter");
    }
    
    public void testDocumentPrinter() {
        checkProcessor("documentPrinter");
    }
    
    public void testSpringPrinter() {
        checkProcessor("springPrinter");
    }
    
    public void testTouch() {
        checkProcessor("touchDocument");
    }
    
    public void testChain() {
        ChainDocumentProcessor chain = (ChainDocumentProcessor)checkProcessor("chain");
        assertNotNull(chain);
        assertNotNull(chain.getProcessors());
        assertEquals(6, chain.getProcessors().size());
    }
    
    public DocumentProcessor checkProcessor(String id) {
        assertTrue(factory.containsBean(id));
        DocumentProcessor proc = (DocumentProcessor)factory.getBean(id);
        assertNotNull(id);
        return proc;
    }

    /**
     * Validates if containsBean returns
     * false for unknown beans
     *
     */
    public void testNotDefined() {
        assertFalse(factory.containsBean("unkownBean"));
    }
    
    public void testLog() {
        Log log = LogFactory.getLog(ProcessorConfigTest.class);
        log.info("hello, world");
    }

}
