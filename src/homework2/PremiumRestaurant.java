package homework2;

import MyExceptions.InvalidPropertyException;
import Utils.Validation;

public class PremiumRestaurant extends Restaurant {
	private double minOrderValue;
	private double orderFeePercentage;
	
	public double getMinOrderValue() { return minOrderValue; }
	public double getOrderFeePercentage() { return orderFeePercentage; }
	
	public PremiumRestaurant(String name, String kitchenType, double rating, boolean isOpen,
			double deliveryFee, double minOrderValue, double orderFeePercentage) throws InvalidPropertyException{
		super(name, kitchenType, rating, isOpen, deliveryFee);
		setMinOrderValue(minOrderValue);
		setOrderFeePercentage(orderFeePercentage);
	}
	
	public PremiumRestaurant(String name, String kitchenType) throws InvalidPropertyException{
		super(name, kitchenType);
		this.minOrderValue = 0;
		this.orderFeePercentage = 0;
	}
	
	public void setMinOrderValue(double minOrderValue) throws InvalidPropertyException{
		if(Validation.validate(minOrderValue)) this.minOrderValue = minOrderValue;
		else throw new InvalidPropertyException("invalid minOrderValue, must be positive value");
	}
	
	public void setOrderFeePercentage(double orderFeePercentage) throws InvalidPropertyException {
		if(Validation.validate(orderFeePercentage)) this.orderFeePercentage = orderFeePercentage;
		else throw new InvalidPropertyException("invalid orderFeePercentage, must be positive value");
	}
	

	@Override
	public double calculatePrice(double base) {
		return base + deliveryFee + (base * (orderFeePercentage / 100)); // base + delivery fee + percentage of base
	}
	
	@Override
	public String toString() {
		return "PremiumRestaurant [minOrderValue=" + minOrderValue + ", orderFeePercentage=" + orderFeePercentage
				+ ", restaurantCode=" + restaurantCode + ", name=" + name + ", kitchenType=" + kitchenType + ", rating="
				+ rating + ", isOpen=" + isOpen + ", deliveryFee=" + deliveryFee + "]";
	}
	
	
	
}
	