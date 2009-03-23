/**
 * 
 */
package ch.panter.demo.strategy;

import java.util.List;

/**
 * @author bseelige
 *
 */
public interface OutputStrategy {

	
	/** outputs the submitted string */
	public void print(String s);
	
	/** outputs the submitted list of strings */
	public void print(List<String> sc);
}
