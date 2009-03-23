/**
 * 
 */
package ch.panter.demo.strategy.impl;

import java.util.List;

import ch.panter.demo.strategy.OutputStrategy;

/**
 * Black-hole outputstrategy
 * 
 * @author bseelige
 *
 */
public class NoopOutputStrategy implements OutputStrategy {

	/* (non-Javadoc)
	 * @see ch.panter.demo.strategy.OutputStrategy#print(java.lang.String)
	 */
	public void print(String s) {
		// do nothing

	}

	/* (non-Javadoc)
	 * @see ch.panter.demo.strategy.OutputStrategy#print(java.util.List)
	 */
	public void print(List<String> sc) {
		// do nothing
	}

}
