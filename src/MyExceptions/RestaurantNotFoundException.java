package MyExceptions;

public class RestaurantNotFoundException extends Exception {
	public RestaurantNotFoundException() {
		super("Restaurant could not be found");
	}
}
