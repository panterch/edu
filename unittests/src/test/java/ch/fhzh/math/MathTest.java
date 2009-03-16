/**
 * 
 */
package ch.fhzh.math;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author bseelige
 *
 */
public class MathTest {
    
    IHigherMath instance;
    
    @Before
    public void setup() {
        instance = new HigherMath();
        
    }
    
    @Test
    public void normalParameter() {
        double res = instance.sqrt(25);
        assertTrue(res == 5.0d);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void negativeParameter() {
        instance.sqrt(-1);
    }
    

}
