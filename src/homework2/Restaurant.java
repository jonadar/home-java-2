package homework2;

import java.util.Comparator;

import MyExceptions.InvalidPropertyException;
import Utils.Validation;

public class Restaurant{
	protected int restaurantCode;
	protected String name;
	protected String kitchenType;
	protected double rating;
	protected boolean isOpen;
	protected double deliveryFee;
	private static int restaurantCount = 1;
	private final static Comparator<Restaurant> comparator = (r1, r2) -> Double.compare(r2.rating, r1.rating);  // from high to low
	
	public int getRestaurantCode() { return restaurantCode; }
	public String getName() { return name; }
	public String getKitchenType() { return kitchenType; }
	public double getRating() { return rating; }
	public boolean isOpen() { return isOpen; }
	public boolean getIsOpen() { return isOpen; } // needed for PropertyValueFactory, don't delete
	public double getDeliveryFee() { return deliveryFee; }
	public static Comparator<Restaurant> getComparator() { return comparator; }
	
	
	public Restaurant(String name, String kitchenType, double rating, boolean isOpen, double deliveryFee) throws InvalidPropertyException {
		setName(name);
		setKitchenType(kitchenType);
		setRating(rating);
		setDeliveryFee(deliveryFee);
		setOpen(isOpen);
		this.restaurantCode = restaurantCount++;
	}
	
	public Restaurant(String name, String kitchenType) throws InvalidPropertyException {
		setName(name);
		setKitchenType(kitchenType);
		setRating(10);
		setOpen(false);
		setDeliveryFee(0);
		this.restaurantCode = restaurantCount++;
	}
	
	public void setName(String name) throws InvalidPropertyException {
		if(Validation.validate(name)) this.name = name;
		else throw new InvalidPropertyException("invalid restaurant name");
	}
	
	public void setKitchenType(String kitchenType) throws InvalidPropertyException {
		if(Validation.isName(kitchenType)) this.kitchenType = kitchenType;
		else throw new InvalidPropertyException("invalid kitchen type");
	}
	
	public void setRating(double rating) throws InvalidPropertyException {
		if(Validation.isNumberInRange(0, 10, rating)) this.rating = rating;
		else throw new InvalidPropertyException("rating must be in range [0 - 10]");
	}
	
	public void setOpen(boolean isOpen) { 
		this.isOpen = isOpen;
	}
	
	public void setDeliveryFee(double deliveryFee) throws InvalidPropertyException {
		if(Validation.validate(deliveryFee)) {
			this.deliveryFee = deliveryFee;	
		}
		else throw new InvalidPropertyException("invalid delivery fee");
	}
	
	/**
	 * @param base price
	 * @return calculated price
	 */
	public double calculatePrice(double base) {
		return base + deliveryFee;
	}
	
	@Override
	public String toString() {
		return "Restaurant [restaurantCode=" + restaurantCode + ", name=" + name + ", kitchenType=" + kitchenType
				+ ", rating=" + rating + ", isOpen=" + isOpen + ", deliveryFee=" + deliveryFee + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Restaurant) {
			Restaurant other = (Restaurant) obj;
			return other.restaurantCode == this.restaurantCode; // didnt do by name aswell since it is sometimes possible to have 2 restuarants with the same name
		}
		return false;
	}
}
