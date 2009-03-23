/**
 * 
 */
package ch.panter.demo.strategy.impl;

import java.util.List;

import com.sun.org.apache.xerces.internal.xs.XSTerm;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import ch.panter.demo.strategy.OutputStrategy;

/**
 * @author bseelige
 *
 */
public class XMLOuputStrategy implements OutputStrategy {

	public void print(String s) {
		XStream xs = new XStream();
		
		System.out.println(xs.toXML(s));
		
	}

	public void print(List<String> sc) {
		// TODO Auto-generated method stub
		
	}


}
