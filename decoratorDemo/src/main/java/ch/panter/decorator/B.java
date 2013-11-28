package ch.panter.decorator;

public class B implements Executable {

	private Executable delegate;
	
	public B(Executable delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public void method() {
		this.delegate.method();
		// additional behaviour
	}


}
