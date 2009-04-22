/**
 * 
 */
package ch.panter.edu.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author seb
 *
 */
public class Util {

	    public static String readFileAsString(Reader input) throws IOException  {
	        StringBuilder fileData = new StringBuilder(1000);
	        BufferedReader reader = new BufferedReader(input);
	        char[] buf = new char[1024];
	        int numRead=0;
	        while((numRead=reader.read(buf)) != -1){
	            fileData.append(buf, 0, numRead);
	        }
	        reader.close();
	        return fileData.toString();
	    }


}
