package ch.fhzh.info2.spring;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Tests all beans defined in processor.xml
 * @author bseelige
 *
 */
public class SpringXMLConfigTest extends TestCase {
    
    /** factory instance under test */
    BeanFactory factory;

    protected void setUp() throws Exception {
        super.setUp();
        ClassPathResource resource = new ClassPathResource("spring.xml");
        factory = new XmlBeanFactory(resource);
    }
    
    public void testSomeBeans() {
    	assertTrue(factory.containsBean("doc"));
    	assertTrue(factory.containsBean("noop"));
    }



}
