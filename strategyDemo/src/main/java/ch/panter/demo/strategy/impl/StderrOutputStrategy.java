/**
 * 
 */
package ch.panter.demo.strategy.impl;

import java.util.List;

import ch.panter.demo.strategy.OutputStrategy;

/**
 * Strategy that prints to stderr
 * 
 * @author bseelige
 *
 */
public class StderrOutputStrategy implements OutputStrategy {

	/* (non-Javadoc)
	 * @see ch.panter.demo.strategy.OutputStrategy#print(java.lang.String)
	 */
	public void print(String s) {
		System.err.println(s);

	}

	/* (non-Javadoc)
	 * @see ch.panter.demo.strategy.OutputStrategy#print(java.util.List)
	 */
	public void print(List<String> sc) {
		// TODO Auto-generated method stub

	}

}
