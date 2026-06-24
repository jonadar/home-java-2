package homework2;

import Utils.Validation;

public class PremiumRestaurant extends Restaurant {
	private double minOrderValue;
	private double orderFeePercentage;
	
	public double getMinOrderValue() { return minOrderValue; }
	public double getOrderFeePercentage() { return orderFeePercentage; }
	
	public PremiumRestaurant(String name, String kitchenType, double rating, boolean isOpen,
			double deliveryFee, double minOrderValue, double orderFeePercentage) {
		super(name, kitchenType, rating, isOpen, deliveryFee);
		this.minOrderValue = minOrderValue;
		this.orderFeePercentage = orderFeePercentage;
	}
	
	public PremiumRestaurant(String name, String kitchenType) {
		super(name, kitchenType);
		this.minOrderValue = 0;
		this.orderFeePercentage = 0;
	}
	
	public void setMinOrderValue(double minOrderValue) {
		boolean valid = Validation.validate(minOrderValue, "invalid minOrderValue");
		
		if(valid) this.minOrderValue = minOrderValue;
	}
	
	public void setOrderFeePercentage(double orderFeePercentage) {
		boolean valid = Validation.validate(orderFeePercentage, "invalid orderFeePercentage");
		
		if(valid) this.orderFeePercentage = orderFeePercentage;
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
	