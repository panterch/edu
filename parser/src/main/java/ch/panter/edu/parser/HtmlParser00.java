/**
 * 
 */
package ch.panter.edu.parser;

import java.io.Reader;
import java.io.Writer;

/**
 * @author seb
 *
 */
public class HtmlParser00 implements HtmlParser {
	
	Reader input;
	Writer output;
	
	/* (non-Javadoc)
	 * @see ch.panter.edu.parser.HtmlParser#parse(java.io.Reader, java.io.Writer)
	 */
	public void parse(Reader input, Writer output) throws Exception {
		this.input = input;
		this.output = output;
		int c;
		while (-1 != (c = input.read())) {
			output.write(c);
		}
	}
		
	//////// getter & setter ////////////////////////////////////////


	/* (non-Javadoc)
	 * @see ch.panter.edu.parser.HtmlParser#getInput()
	 */
	public Reader getInput() {
		return input;
	}


	/* (non-Javadoc)
	 * @see ch.panter.edu.parser.HtmlParser#setInput(java.io.Reader)
	 */
	public void setInput(Reader input) {
		this.input = input;
	}


	/* (non-Javadoc)
	 * @see ch.panter.edu.parser.HtmlParser#getOutput()
	 */
	public Writer getOutput() {
		return output;
	}


	/* (non-Javadoc)
	 * @see ch.panter.edu.parser.HtmlParser#setOutput(java.io.Writer)
	 */
	public void setOutput(Writer output) {
		this.output = output;
	}
	
	

}
