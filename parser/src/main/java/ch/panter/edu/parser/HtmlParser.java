package ch.panter.edu.parser;

import java.io.Reader;
import java.io.Writer;

public interface HtmlParser {

	public abstract void parse(Reader input, Writer output) throws Exception;

	public abstract Reader getInput();

	public abstract void setInput(Reader input);

	public abstract Writer getOutput();

	public abstract void setOutput(Writer output);

}