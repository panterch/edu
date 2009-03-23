/**
 * 
 */
package ch.panter.demo.strategy.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ch.panter.demo.strategy.OutputStrategy;
import ch.panter.demo.strategy.OutputStrategyFactory;

/**
 * CLI Main class to demonstrate different strategies
 * 
 * @author bseelige
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Main m = new Main();
		m.mainLoop();

	}

	/**
	 * loops trough methods to query user for
	 * params and ouputs text.
	 *
	 */
	private void mainLoop() {
        
		while(true) {
			try {
				flushStreams();
				showInfo();
				int strategyNo = getStrategyNo();
				OutputStrategy o = OutputStrategyFactory.getStrategy(strategyNo);
				String text = getText();
				o.print(text);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        
        
        
		
	}

	/** flushes all streams used */
	private void flushStreams() {
		System.out.flush();
		System.err.flush();
		
	}

	/** retrieves a string from the user */
	private String getText() throws IOException {
		System.out.println("Type text to output:");
		return readLn();
	}

	/** retrieves a strategy no from the user */
	private int getStrategyNo() throws IOException {
		System.out.print("Choose strategy #: ");
		String s = readLn();
		return Integer.parseInt(s);
	}

	/** reads a line from stdin */
	private String readLn() throws IOException {
		 InputStreamReader input = new InputStreamReader(System.in);
		 BufferedReader reader = new BufferedReader(input);
	     return reader.readLine(); 
	}

	/** shows program info an available options */
	private void showInfo() {
		System.out.println("\n***** Strategy Demo *******\n");
		System.out.println(OutputStrategyFactory.getDescription());		
		
	}

}
