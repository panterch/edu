/**
 * 
 */
package ch.fhzh.math;

/**
 * @author bseelige
 *
 */
public class HigherMath implements IHigherMath {

    public double sqrt(int i) throws IllegalArgumentException {
        if (0 > i) {
            throw new IllegalArgumentException("negative parameter not allowed");
        }
        return Math.sqrt(i);
    }

 

}
