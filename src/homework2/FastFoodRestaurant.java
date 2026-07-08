package homework2;

import MyExceptions.InvalidPropertyException;
import Utils.Validation;

public class FastFoodRestaurant extends Restaurant {
	private double averageCookTime;
	private double fastDeliveryFee;
	
	public double getAverageCookTime() { return averageCookTime; }
	public double getFastDeliveryFee() { return fastDeliveryFee;	}
	
	
	public FastFoodRestaurant(String name, String kitchenType, double rating, boolean isOpen,
			double deliveryFee, double averageCookTime, double fastDeliveryFee) throws InvalidPropertyException {
		super(name, kitchenType, rating, isOpen, deliveryFee);
		setAverageCookTime(averageCookTime);
		setFastDeliveryFee(fastDeliveryFee);
	}


	public FastFoodRestaurant(String name, String kitchenType) throws InvalidPropertyException {
		super(name, kitchenType);
		this.averageCookTime = 10;
		this.fastDeliveryFee = 0;
	}

	public void setAverageCookTime(double averageCookTime) throws InvalidPropertyException {

		if(Validation.validate(averageCookTime)) this.averageCookTime = averageCookTime;
		else throw new InvalidPropertyException("invalid averageCookTime, must be positive value");
	}
	
	public void setFastDeliveryFee(double fastDeliveryFee) throws InvalidPropertyException {
		if(Validation.validate(fastDeliveryFee)) this.fastDeliveryFee = fastDeliveryFee;
		else throw new InvalidPropertyException("invalid fastDeliveryFee, must be positive value");
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
