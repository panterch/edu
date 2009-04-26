/**
 * 
 */
package ch.fhzh.info2.junit;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import static org.junit.Assert.*;

/**
 * @author bseelige
 *
 */
public class Junit4Test {
    
    public static void main(String[] args) {
        JUnitCore.runClasses(Junit4Test.class);
    }

    @Test
    public void foo() {
        System.out.println("test foo");
        assertTrue(true);
    }

}
