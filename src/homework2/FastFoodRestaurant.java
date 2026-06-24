package homework2;

import Utils.Validation;

public class FastFoodRestaurant extends Restaurant {
	private double averageCookTime;
	private double fastDeliveryFee;
	
	public double getAverageCookTime() { return averageCookTime; }
	public double getFastDeliveryFee() { return fastDeliveryFee;	}
	
	
	public FastFoodRestaurant(String name, String kitchenType, double rating, boolean isOpen,
			double deliveryFee, double averageCookTime, double fastDeliveryFee) {
		super(name, kitchenType, rating, isOpen, deliveryFee);
		this.averageCookTime = averageCookTime;
		this.fastDeliveryFee = fastDeliveryFee;
	}


	public FastFoodRestaurant(String name, String kitchenType) {
		super(name, kitchenType);
		this.averageCookTime = 10;
		this.fastDeliveryFee = 0;
	}

	public void setAverageCookTime(double averageCookTime) {
		boolean valid = Validation.validate(averageCookTime, "invalid averageCookTime");
		
		if(valid) this.averageCookTime = averageCookTime;
	}
	
	public void setFastDeliveryFee(double fastDeliveryFee) {
		boolean valid = Validation.validate(fastDeliveryFee, "invalid fastDeliveryFee");
		
		if(valid) this.fastDeliveryFee = fastDeliveryFee;
	}
	
	@Override
	public double calculatePrice(double base) {
		return base + deliveryFee + fastDeliveryFee;
	}
	
	@Override
	public String toString() {
		return "FastFoodRestaurant [averageCookTime=" + averageCookTime + ", fastDeliveryFee=" + fastDeliveryFee
				+ ", restaurantCode=" + restaurantCode + ", name=" + name + ", kitchenType=" + kitchenType + ", rating="
				+ rating + ", isOpen=" + isOpen + ", deliveryFee=" + deliveryFee + "]";
	}
	
	
	
	
	
}
