package homework2;

import java.util.Comparator;

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
	
	
	public Restaurant(String name, String kitchenType, double rating, boolean isOpen,
			double deliveryFee) {
		this.restaurantCode = restaurantCount++;
		this.name = name;
		this.kitchenType = kitchenType;
		this.rating = rating;
		this.isOpen = isOpen;
		this.deliveryFee = deliveryFee;
	}
	
	public Restaurant(String name, String kitchenType) {
		this.restaurantCode = restaurantCount++;
		this.name = name;
		this.kitchenType = kitchenType;
		this.rating = 10;
		this.isOpen = false;
		this.deliveryFee = 0;
	}
	
	public void setName(String name) {
		boolean valid = Validation.validate(name, "invalid restaurant name");
		
		if(valid) this.name = name;
	}
	
	public void setKitchenType(String kitchenType) {
		if(Validation.isName(kitchenType)) this.kitchenType = kitchenType;
		else System.out.println("invalid kitchen type");
	}
	
	public void setRating(double rating) {
		boolean valid = Validation.isNumberInRange(0, 10, rating, "rating");
		
		if(valid) this.rating = rating;
	}
	
	public void setOpen(boolean isOpen) { 
		this.isOpen = isOpen;
	}
	
	public void setDeliveryFee(double deliveryFee) {
		boolean valid = Validation.validate(deliveryFee, "invalid delivery fee");
		
		if(valid) this.deliveryFee = deliveryFee;
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
