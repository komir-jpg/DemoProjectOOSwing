package DemoUninaSN.OO_Project;

public class InvalidUsername extends Exception implements InsertionError {
	@Override
	public void campoMancante() {
		System.out.println("inserici username");
	}
}
