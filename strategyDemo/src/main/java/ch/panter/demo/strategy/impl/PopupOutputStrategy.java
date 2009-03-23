/**
 * 
 */
package ch.panter.demo.strategy.impl;

import java.util.List;

import javax.swing.JFrame;

import ch.panter.demo.strategy.OutputStrategy;

/**
 * @author bseelige
 *
 */
public class PopupOutputStrategy implements OutputStrategy {

	/* (non-Javadoc)
	 * @see ch.panter.demo.strategy.OutputStrategy#print(java.lang.String)
	 */
	public void print(String s) {
		JFrame mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle(s);
		mainFrame.pack();
		mainFrame.setVisible(true);

	}

	/* (non-Javadoc)
	 * @see ch.panter.demo.strategy.OutputStrategy#print(java.util.List)
	 */
	public void print(List<String> sc) {
		// TODO Auto-generated method stub

	}

}
