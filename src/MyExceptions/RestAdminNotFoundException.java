package MyExceptions;

public class RestAdminNotFoundException extends Exception {
	public RestAdminNotFoundException() {
		super("Restaurant admin could not be found");
	}
}
