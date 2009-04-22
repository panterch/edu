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
public class HtmlParser01 implements HtmlParser {
	
	enum STATE { 
		UNKNOWN, IN_BODY
	}
	
	private STATE state = STATE.UNKNOWN;
	private StringBuilder buf = null;
	private char c;
	private int cnt = 0;
	
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
			case IN_BODY:
				this.handleInBody();
				break;
			default:
				this.handleUnknown();
				break;
			}
		}
	}
	
	private void handleUnknown() throws Exception {
		// start of a tag
		if ('<' == c && null == buf) {
			this.buf = new StringBuilder();
		// end of a tag
		} else if ('>' == c && null != buf) {
			// the parser has to detect body tags
			if ("body".equals(buf.toString())) {
				this.state = STATE.IN_BODY;
			}
			this.buf = null;
		// we are inside a tag, fill buffer with tag cname
		} else if (null != buf) {
			this.buf.append(this.c);
		// outside a tag, ignore char
		} else { }
	}
	


	private void handleInBody() throws Exception {
		if ('<' == this.c) {
			this.state = STATE.UNKNOWN;
			handleUnknown();
			return;
		}
		output.append(this.c);
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
