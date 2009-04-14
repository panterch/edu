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
public class HtmlParser02 implements HtmlParser {
	
	enum STATE { 
		UNKNOWN, IN_TAG
	}
	
	private STATE state = STATE.UNKNOWN;
	private StringBuilder buf = null;
	private char c;
	private int cnt = 0;
	private int stack = 0;
	
	Reader input;
	Writer output;
	
	
	/* (non-Javadoc)
	 * @see ch.panter.edu.parser.HtmlParser#parse(java.io.Reader, java.io.Writer)
	 */
	public void parse (Reader input, Writer output) throws Exception {
		this.input = input;
		this.output = output;
		int i;
		while (-1 != (i = input.read())) {
			this.c = (char)i;
			this.cnt++;
			switch (this.state) {
			case IN_TAG:
				this.handleInTag();
				break;
			default:
				this.handleUnknown();
				break;
			}
		}
	}
	
	private void handleInTag() throws Exception {
		// this is a closing tag
		if ('/' == c) {
			this.stack--;
			this.state = STATE.UNKNOWN;
		// end of a tag
		} else if ('>' == c) {
			for (int i=0; i<stack; i++) {
				this.output.write("  ");
			}
			this.output.write(this.buf.toString());
			this.output.write('\n');
			stack++;
			this.state = STATE.UNKNOWN;
		// we are inside a tag, fill buffer with tag cname
		} else if (null != buf) {
			this.buf.append(this.c);
		}
	}
	


	private void handleUnknown() throws Exception {
		// detect start of tag
		if ('<' == this.c) {
			this.state = STATE.IN_TAG;
			this.buf = new StringBuilder();
			return;
		}
	}
	
	private void raiseException() throws Exception {
		throw new Exception("Parse Error: cnt -> "+cnt+" c -> "+this.c+
				" state -> "+ this.state+" buffer -> "+ this.buf);
		
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
