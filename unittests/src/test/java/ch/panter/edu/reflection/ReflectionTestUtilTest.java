/**
 * 
 */
package ch.panter.edu.reflection;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * @author bseelige
 *
 */
public class ReflectionTestUtilTest {
    
    ReflectionTestUtil instance;
    ValueObject vo;
    
    @Before
    public void setup() {
        instance = new ReflectionTestUtil();
        vo = new ValueObject();
    }
    
    @Test
    public void testSetField() throws Exception {
        instance.setField(vo, "field", "junit", String.class);
        String res = vo.toString();
        Assert.assertNotNull(res);
        Assert.assertEquals("junit", res);
    }
}
