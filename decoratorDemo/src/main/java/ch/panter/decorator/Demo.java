package ch.panter.decorator;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Executable a = new A();
		Executable b = new B(a);
	}

}
