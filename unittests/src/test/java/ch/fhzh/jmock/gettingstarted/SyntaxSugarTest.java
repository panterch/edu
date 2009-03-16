/**
 * 
 */
package ch.fhzh.jmock.gettingstarted;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

/**
 * @author bseelige
 *
 */
public class SyntaxSugarTest {

    @Test
    public void testBraceWhitespace() {
        StringBuilder sb = new StringBuilder();
        sb.append ("test");
        assertEquals("test", sb.toString());
    }
    
    @Test
    public void doubleBrace() {
        Collection<String> col = new ArrayList<String>() {{
            add("test1");
            add("test2");
        }};
        assertEquals(2, col.size());
    }

}
