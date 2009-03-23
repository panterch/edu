/**
 * 
 */
package ch.panter.demo.strategy.impl;

import java.util.List;

import ch.panter.demo.strategy.OutputStrategy;

/**
 * @author bseelige
 *
 */
public class TimeOutputStrategy implements OutputStrategy {

	
	OutputStrategy delegate = new StdoutOutputStrategy();
	
	/* (non-Javadoc)
	 * @see ch.panter.demo.strategy.OutputStrategy#print(java.lang.String)
	 */
	public void print(String s) {
		s = "["+System.currentTimeMillis()+"] "+s;
		delegate.print(s);
	}

	/* (non-Javadoc)
	 * @see ch.panter.demo.strategy.OutputStrategy#print(java.util.List)
	 */
	public void print(List<String> sc) {
		// TODO Auto-generated method stub

	}

}
