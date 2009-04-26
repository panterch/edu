package ch.fhzh.info2.spring;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import ch.fhzh.info2.document.Document;
import ch.fhzh.info2.document.DocumentImpl;

/**
 * really simple demonstration of spring
 * IOC framework possibilities
 * @author bseelige
 *
 */
public class SimpleBeanTest extends TestCase {

    
    /** 
     * reads simple.xml bean definition and tests acces
     * to a bean defined there
     *
     */
    public void testBeanAccess() {
        ClassPathResource resource = new ClassPathResource("simple.xml");
        assertTrue("simple.xml not found", resource.exists());
        BeanFactory factory = new XmlBeanFactory(resource);
        assertNotNull("no factory",factory);
        assertTrue("no doc", factory.containsBean("doc"));
        //Document doc = new DocumentImpl();
        Document doc = (Document)factory.getBean("doc");
        assertNotNull("doc null", doc);
        assertEquals("junit!", doc.getId());
    }

}
