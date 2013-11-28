package ch.panter.decorator.inputstream;

import java.io.*;


public class InputDemo {
	public static void main(String[] args) throws IOException {
		int c;

		try {

			InputStream in = new FileInputStream("src/test/resources/test.txt");

			in = new BufferedInputStream(in);
			
			in = new LowerCaseInputStream(in);

			while ((c = in.read()) >= 0) {
				System.out.print((char) c);
			}

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
