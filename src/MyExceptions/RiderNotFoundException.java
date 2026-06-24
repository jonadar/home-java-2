package MyExceptions;

public class RiderNotFoundException extends Exception {
	public RiderNotFoundException() {
		super("rider could not be found");
	}
}
