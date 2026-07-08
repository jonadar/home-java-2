package homework2;

import java.util.Comparator;

import MyExceptions.InvalidPropertyException;
import Utils.Validation;

public class Order {
	private int orderCode;
	private int customerCode;
	private Restaurant restaurant;
	private int restaurantCode;
	private String driverId;
	private String orderDate;
	private String deliveryDate;
	private double basePrice;
	private double finalPrice;
	private String deliveryStatus;
	
	private static int orderCount = 1;
	
	private static final String[] VALID_STATUSES = {"sent", "on the way", "delivered"};  // order sent - sent  to system, order on the way - rider is delivering, delivered - order arrived
	private static final Comparator<Order> comparator = (c1, c2) -> Double.compare(c2.finalPrice, c1.finalPrice); // from high to low
	
	public int getOrderCode() {return orderCode;}
	public int getCustomerCode() {return customerCode;}
	public Restaurant getRest() {return restaurant;}
	public int getRestaurantCode() {return restaurantCode;}
	public String getDriverId() {return driverId;}
	public String getOrderDate() {return orderDate;}
	public String getDeliveryDate() {return deliveryDate;}
	public double getBasePrice() {return basePrice;}
	public double getFinalPrice() {return finalPrice;}
	public String getDeliveryStatus() {return deliveryStatus;}
	public static String[] getValidStatuses() {return VALID_STATUSES;}
	public static Comparator<Order> getComparator() { return comparator; }
	
	public Order(int customerCode, Restaurant restaurant, double basePrice, String orderDate) throws InvalidPropertyException {
		setCustomerCode(customerCode);
		setRestaurant(restaurant);
		setRestaurantCode(restaurant.getRestaurantCode());
		setOrderDate(orderDate);
		setBasePrice(basePrice);
		setDeliveryStatus(VALID_STATUSES[0]);
		this.driverId = null;
		this.deliveryDate = "0";
		this.orderCode = orderCount++;
		
		System.out.println("Order with code: " + this.orderCode + " has been created.");
	}
	
	public void setCustomerCode(int customerCode) throws InvalidPropertyException{
		boolean valid = Validation.validate(customerCode);
		if(valid) this.customerCode = customerCode;
		else throw new InvalidPropertyException("invalid customer code, must be positive value");
	}
	
	public void setRestaurant(Restaurant rest) throws InvalidPropertyException{
		if (rest != null) {
			this.restaurant = rest;
		}
		else throw new InvalidPropertyException("invalid restaurant, must be valid none null Restaurant");
	}
	
	public void setRestaurantCode(int restaurantCode) throws InvalidPropertyException{
		boolean valid = Validation.validate(restaurantCode);
		if(valid) this.restaurantCode = restaurantCode;
		else throw new InvalidPropertyException("invalid restaurant code, must be positive value");
	}
	
	public void setDriverId(String driverId) throws InvalidPropertyException{
		if(Validation.isId(driverId)) this.driverId = driverId;
		else throw new InvalidPropertyException("invalid driver id, must be 9 digits");
	}
	
	public void setOrderDate(String orderDate) throws InvalidPropertyException{
		if(Validation.isDate(orderDate)) this.orderDate = orderDate;
		else throw new InvalidPropertyException("invalid order date, must be in dd/mm/yyyy format between the years 2000 and 2026");
	}
	
	public void setDeliveryDate(String deliveryDate) throws InvalidPropertyException{
		if(Validation.isDate(deliveryDate)) this.deliveryDate = deliveryDate;
		else throw new InvalidPropertyException("invalid delivery date, must be in dd/mm/yyyy format between the years 2000 and 2026");
	}
	
	public void setBasePrice(double basePrice) throws InvalidPropertyException{
		boolean valid = Validation.validate(basePrice);
		if(valid) {
			this.basePrice = basePrice;
			this.finalPrice = restaurant.calculatePrice(basePrice);
		} else throw new InvalidPropertyException("invalid base price, must be positive value");
	}
	
	public void setDeliveryStatus(String deliveryStatus) throws InvalidPropertyException{
		boolean inArray = !Validation.validateNotInArray(deliveryStatus, VALID_STATUSES);
		
		if(inArray) this.deliveryStatus = deliveryStatus;
		else throw new InvalidPropertyException("invalid delivery status");
	}
	
	@Override
	public String toString() {
		return "Order [orderCode=" + orderCode + ", customerCode=" + customerCode + ", restaurant=" + restaurant
				+ ", restaurantCode=" + restaurantCode + ", driverId=" + driverId + ", orderDate=" + orderDate
				+ ", deliveryDate=" + deliveryDate + ", basePrice=" + basePrice + ", finalPrice=" + finalPrice
				+ ", deliveryStatus=" + deliveryStatus + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Order) {
			Order other = (Order) obj;
			return other.orderCode == this.orderCode;
		}
		return false;
	}
}
