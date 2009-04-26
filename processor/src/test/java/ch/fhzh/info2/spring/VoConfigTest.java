package ch.fhzh.info2.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import ch.fhzh.info2.document.Document;
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
public class VoConfigTest extends TestCase {
    
    /** factory instance under test */
    BeanFactory factory;

    protected void setUp() throws Exception {
        super.setUp();
        ClassPathResource resource = new ClassPathResource("vo.xml");
        factory = new XmlBeanFactory(resource);
    }
    
    public void testDocument() {
    	assertTrue(factory.containsBean("doc"));
    	Document doc = (Document)factory.getBean("doc");
    	assertNotNull(doc);
    }


}
