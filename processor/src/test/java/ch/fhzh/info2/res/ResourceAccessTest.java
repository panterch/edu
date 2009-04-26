package ch.fhzh.info2.res;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import junit.framework.TestCase;

import org.springframework.core.io.ClassPathResource;

public class ResourceAccessTest extends TestCase {
    
    final String id = "res.properties";

    public static void main(String[] args) {
        junit.textui.TestRunner.run(ResourceAccessTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testAccessJdkMeans() throws IOException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream stream = cl.getResourceAsStream(id);
        assertNotNull(stream);
        checkProperty(stream);
        
    }
    
    public void testAccessSpring() throws IOException {
        ClassPathResource res = new ClassPathResource(id);
        assertNotNull(res);
        assertTrue(res.exists());
        checkProperty(res.getInputStream()); 
    }

    private void checkProperty(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        String mode = properties.getProperty("mode");
        assertNotNull("property mode not defined", mode);
        assertEquals("not in test mode", mode, "test");
    }
    
    

}
