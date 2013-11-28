package ch.panter.decorator.reader;

import java.io.BufferedReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringReader;

public class ReaderDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
		{
		  String s = "<html>Hallo! <p>Content im Tag.</p>\n"
		             + "Content ausserhalb Tag.</html>";

		  Reader reader = new StringReader( s );
		  BufferedReader in = new BufferedReader( reader );

		  for ( String line; (line = in.readLine()) != null; )
		    System.out.println( line );

		  in.close();
		}
		catch ( Exception e )
		{
		  e.printStackTrace();
		}
	}

}
