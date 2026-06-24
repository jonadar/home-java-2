package MyExceptions;

public class CustomerNotFoundException extends Exception {
	public CustomerNotFoundException() {
		super("customer could not be found");
	}
}
