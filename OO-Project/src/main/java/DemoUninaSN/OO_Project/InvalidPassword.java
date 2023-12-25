package DemoUninaSN.OO_Project;

public class InvalidPassword extends InvalidUsername implements InsertionError {

	@Override
	public void campoMancante() {
		System.out.println("inserisci password");

	}

}
