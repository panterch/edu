/**
 * 
 */
package ch.panter.demo.strategy.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ch.panter.demo.strategy.OutputStrategy;

/**
 * @author bseelige
 *
 */
public class LoggingOutputStrategy implements OutputStrategy {
	
	private static Log log = LogFactory.getLog(LoggingOutputStrategy.class);

	/* (non-Javadoc)
	 * @see ch.panter.demo.strategy.OutputStrategy#print(java.lang.String)
	 */
	public void print(String s) {
		log.info(s);

	}

	/* (non-Javadoc)
	 * @see ch.panter.demo.strategy.OutputStrategy#print(java.util.List)
	 */
	public void print(List<String> sc) {
		// TODO Auto-generated method stub

	}

}
